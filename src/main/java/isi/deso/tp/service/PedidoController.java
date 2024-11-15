package isi.deso.tp.service;

import isi.deso.tp.dao.ClienteMemoryDAO;
import isi.deso.tp.dao.PedidoDAO;
import isi.deso.tp.exception.MetodoPagoNoEncontradoException;
import isi.deso.tp.exception.VendedorNoUnicoException;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.ContextoPago;
import isi.deso.tp.model.DTO.PedidoDTO;
import isi.deso.tp.model.EfectivoStrategy;
import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.MercadoPagoStrategy;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.TransferenciaStrategy;
import isi.deso.tp.model.Vendedor;
import java.util.List;

public class PedidoController {

    private PedidoDAO pedidoDAO;

    public PedidoController(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public Pedido crearPedido(Integer id, Cliente cliente, List<ItemPedido> pedidoDetalle) {
        Pedido pedido = null;
        try {
            vendedorEsUnico(pedidoDetalle);
            pedido = new Pedido(id, cliente, pedidoDetalle);
            pedidoDAO.agregarPedidoALista(pedido);
            ClienteController clienteController = new ClienteController(ClienteMemoryDAO.getInstance());
            clienteController.agregarPedido(cliente, pedido);
        } catch (VendedorNoUnicoException excep) {
            System.err.println("Desde PedidoController: " + excep.getMessage());
        }
        return pedido;
    }

    public Pedido crearPedido(Integer id, Cliente cliente, EstadoPedidoEnum estadoPedido, List<ItemPedido> pedidoDetalle, Double precioTotal) {
        Pedido pedido = null;
        try {
            vendedorEsUnico(pedidoDetalle);
            pedido = new Pedido(id, cliente, estadoPedido, pedidoDetalle, precioTotal);
            pedidoDAO.agregarPedidoALista(pedido);
            ClienteController clienteController = new ClienteController(ClienteMemoryDAO.getInstance());
            clienteController.agregarPedido(cliente, pedido);
        } catch (VendedorNoUnicoException excep) {
            System.err.println("Desde PedidoController: " + excep.getMessage());
        }
        return pedido;

    }

    public void vendedorEsUnico(List<ItemPedido> pedidoDetalle) throws VendedorNoUnicoException {
        if (!(pedidoDetalle.isEmpty())) {
            Vendedor vendedor = pedidoDetalle.getFirst().getVendedor();
            Boolean vendedorEsUnico = pedidoDetalle.stream().allMatch(item -> item.getVendedor().equals(vendedor));

            if (!vendedorEsUnico) {
                throw new VendedorNoUnicoException("Vendedor no es unico para pedidoDetalle");
            }
        }
    }

    public void agregarItemPedido(Pedido pedido, ItemPedido itemPedido) {
        try {
            pedido.addPedidoDetalle(itemPedido);
            vendedorEsUnico(pedido.getPedidoDetalle());
        } catch (VendedorNoUnicoException excep) {
            System.err.println("Desde PedidoController: " + excep.getMessage());
        }

    }

    public Double calcularParcial(Pedido pedido) {
        return pedido.getPedidoDetalle().stream().mapToDouble(ItemPedido::getPrecio).sum();
    }

    public Pedido convertirPedidoDesdeDTO(PedidoDTO pedidoDTO) {
        Pedido pedidoNuevo = null;
        ItemPedidoController itemPedidoController = new ItemPedidoController();
        ClienteController clienteController = new ClienteController(ClienteMemoryDAO.getInstance());
        List<ItemPedido> pedidoDetalle = itemPedidoController.convertirDesdeListaDTO(pedidoDTO.getListaItemPedidoDTO());
        try {
            vendedorEsUnico(pedidoDetalle);
            pedidoNuevo = crearPedido(pedidoDTO.getId(), clienteController.getClienteDAO().buscarPorIdCliente(pedidoDTO.getIdCliente()), pedidoDetalle);
            clienteController.agregarPedido(pedidoNuevo.getCliente(), pedidoNuevo);

        } catch (VendedorNoUnicoException excep) {
            System.err.println("Desde PedidoController: " + excep.getMessage());
        }
        return pedidoNuevo;
    }

    public void actualizarEstadoPedido(Pedido pedido, EstadoPedidoEnum estadoNuevo) {
        pedido.setEstadoPedido(estadoNuevo);
    }

    public Double aplicarRecargo(Pedido pedido) {
        Double precioTotal = pedido.getContextoPago().agregarRecargo(calcularParcial(pedido));

        System.out.println("Desde PedidoController: El cliente realiza el pago de $" + precioTotal + " con " + pedido.getContextoPago().getPagoStrategy().toString());

        return precioTotal;
    }

    public Double aplicarRecargoPorDTO(PedidoDTO pedidoDTO) {
        ContextoPago contextoPago = null;
        try {
            switch (pedidoDTO.getMetodoPago()) {
                case 1 ->
                    contextoPago = new ContextoPago(new EfectivoStrategy());
                case 2 ->
                    contextoPago = new ContextoPago(new TransferenciaStrategy(pedidoDTO.getCuit(), pedidoDTO.getCuit()));
                case 3 ->
                    contextoPago = new ContextoPago(new MercadoPagoStrategy(pedidoDTO.getAlias()));
                default -> {
                    metodoPagoNoEncontrado();
                }
            }
        } catch (MetodoPagoNoEncontradoException exception) {
            System.out.println("Desde PedidoController: " + exception.getMessage());
        }

        if (contextoPago != null) {
            Double precioTotal = contextoPago.agregarRecargo(pedidoDTO.getPrecioTotal());
            System.out.println("Desde PedidoController: El cliente realiza el pago de $" + precioTotal + " por " + contextoPago.getPagoStrategy().nombreEstrategia());
            return precioTotal;
        } else {
            return -1.0;
        }
    }

    public void metodoPagoNoEncontrado() throws MetodoPagoNoEncontradoException {
        throw new MetodoPagoNoEncontradoException();
    }

    public List<Pedido> filtrarPorEstado(List<Pedido> listaPedidos, EstadoPedidoEnum estadoPedido) {
        return listaPedidos.stream().filter(pedido -> pedido.getEstadoPedido() == estadoPedido).toList();
    }

    public List<Pedido> buscarPorRestaurante(Integer idVendedor) {
        return pedidoDAO.buscarPorIdVendedor(idVendedor);
    }

    public List<Pedido> buscarPorIdPedido(Integer idPedido) {
        return pedidoDAO.buscarPorIdPedido(idPedido);
    }

    public void actualizarEstado(EstadoPedidoEnum estadoPedidoNuevo, Pedido pedido) {
        pedido.setChange(estadoPedidoNuevo);
    }

    public void generarPagoPara(Pedido pedido) {
        pedido.getContextoPago().getPagoStrategy().generarPago(pedido);
    }

    public void agregarPedidoALista(Pedido pedido) {
        pedidoDAO.getListaPedidos().add(pedido);
    }

    public List<Pedido> getListaPedidos() {
        return pedidoDAO.getListaPedidos();
    }

    public Pedido buscarPorNombreCliente(String cliente) {
        for (Pedido pedido : pedidoDAO.getListaPedidos()) {
            if (pedido.getCliente().getNombre().equals(cliente)) {
                return pedido;
            }
        }
        return null;
    }
}
