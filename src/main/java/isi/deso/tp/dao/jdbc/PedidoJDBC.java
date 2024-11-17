package isi.deso.tp.dao.jdbc;

import isi.deso.tp.dao.PedidoDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.ContextoPago;
import isi.deso.tp.model.EfectivoStrategy;
import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.MercadoPagoStrategy;
import isi.deso.tp.model.PagoStrategy;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.TransferenciaStrategy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoJDBC implements PedidoDAO {

    private final ItemMenuJDBC itemMenuJDBC;
    private final ClienteJDBC clienteJDBC;
    private final ItemsPedidoJDBC itemsPedidoJDBC;

    public PedidoJDBC() {
        this.itemMenuJDBC = new ItemMenuJDBC();
        this.clienteJDBC = new ClienteJDBC();
        this.itemsPedidoJDBC = new ItemsPedidoJDBC();
    }

    @Override
    public List<Pedido> listarPedidos() {
        String queryPedidos = "SELECT id, cliente_id, estado_pedido, precio_total, contexto_pago_id FROM Pedido";
        String queryDetalle = "SELECT id, item_menu_id, cantidad, precio FROM ItemPedido WHERE pedido_id = ?";
        String queryContextoPago = "SELECT estrategia_pago_id FROM ContextoPago WHERE id = ?";
        String queryPago = "SELECT id, tipo_estrategia FROM Pago WHERE id = ?";
        String queryEfectivo = "SELECT id FROM Efectivo WHERE id = ?";
        String queryTransferencia = "SELECT id, cuit, cbu FROM Transferencia WHERE id = ?";
        String queryMercadoPago = "SELECT id, alias FROM MercadoPago WHERE id = ?";

        List<Pedido> pedidos = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBConnector.getInstance();
            conn.setAutoCommit(false);

            try (PreparedStatement psPedidos = conn.prepareStatement(queryPedidos); ResultSet rsPedidos = psPedidos.executeQuery()) {
                while (rsPedidos.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rsPedidos.getInt("id"));
                    int clienteId = rsPedidos.getInt("cliente_id");
                    Cliente cliente = clienteJDBC.buscarClientePorId(clienteId);
                    pedido.setCliente(cliente);
                    pedido.setEstadoPedido(EstadoPedidoEnum.valueOf(rsPedidos.getString("estado_pedido")));
                    pedido.setPrecioTotal(rsPedidos.getDouble("precio_total"));

                    // Obtener ContextoPago y Pago
                    int contextoPagoId = rsPedidos.getInt("contexto_pago_id");
                    if (contextoPagoId != 0) {
                        try (PreparedStatement psContextoPago = conn.prepareStatement(queryContextoPago)) {
                            psContextoPago.setInt(1, contextoPagoId);
                            try (ResultSet rsContextoPago = psContextoPago.executeQuery()) {
                                if (rsContextoPago.next()) {
                                    int estrategiaPagoId = rsContextoPago.getInt("estrategia_pago_id");

                                    // Consultar la estrategia de Pago
                                    try (PreparedStatement psPago = conn.prepareStatement(queryPago)) {
                                        psPago.setInt(1, estrategiaPagoId);
                                        try (ResultSet rsPago = psPago.executeQuery()) {
                                            if (rsPago.next()) {
                                                String tipoEstrategia = rsPago.getString("tipo_estrategia");
                                                PagoStrategy pagoStrategy = null;

                                                switch (tipoEstrategia) {
                                                    case "EFECTIVO":
                                                        try (PreparedStatement psEfectivo = conn.prepareStatement(queryEfectivo)) {
                                                            psEfectivo.setInt(1, estrategiaPagoId);
                                                            try (ResultSet rsEfectivo = psEfectivo.executeQuery()) {
                                                                if (rsEfectivo.next()) {
                                                                    pagoStrategy = new EfectivoStrategy();
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case "TRANSFERENCIA":
                                                        try (PreparedStatement psTransferencia = conn.prepareStatement(queryTransferencia)) {
                                                            psTransferencia.setInt(1, estrategiaPagoId);
                                                            try (ResultSet rsTransferencia = psTransferencia.executeQuery()) {
                                                                if (rsTransferencia.next()) {
                                                                    TransferenciaStrategy transferenciaStrategy = new TransferenciaStrategy(rsTransferencia.getString("cuit"), rsTransferencia.getString("cbu"));

                                                                    pagoStrategy = transferenciaStrategy;
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case "MERCADOPAGO":
                                                        try (PreparedStatement psMercadoPago = conn.prepareStatement(queryMercadoPago)) {
                                                            psMercadoPago.setInt(1, estrategiaPagoId);
                                                            try (ResultSet rsMercadoPago = psMercadoPago.executeQuery()) {
                                                                if (rsMercadoPago.next()) {
                                                                    MercadoPagoStrategy mercadoPagoStrategy = new MercadoPagoStrategy(rsMercadoPago.getString("alias"));

                                                                    pagoStrategy = mercadoPagoStrategy;
                                                                }
                                                            }
                                                        }
                                                        break;
                                                }

                                                if (pagoStrategy != null) {
                                                    ContextoPago contextoPago = new ContextoPago(pagoStrategy);
                                                    pedido.setContextoPago(contextoPago);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Obtener los detalles del pedido (ItemPedido)
                    List<ItemPedido> detalles = new ArrayList<>();
                    try (PreparedStatement psDetalle = conn.prepareStatement(queryDetalle)) {
                        psDetalle.setInt(1, pedido.getId());
                        try (ResultSet rsDetalle = psDetalle.executeQuery()) {
                            while (rsDetalle.next()) {
                                ItemPedido item = new ItemPedido();
                                item.setId(rsDetalle.getInt("id"));
                                item.setCantidad(rsDetalle.getInt("cantidad"));
                                item.setPrecio(rsDetalle.getDouble("precio"));
                                int itemMenuId = rsDetalle.getInt("item_menu_id");
                                ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).getFirst();
                                item.setItemMenu(itemMenu);

                                detalles.add(item);
                            }
                        }
                    }
                    pedido.setPedidoDetalle(detalles);

                    pedidos.add(pedido);
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, "Error al listar pedidos", ex);
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, "Error al realizar rollback", rollbackEx);
                }
            }
        }
        return pedidos;
    }

    @Override
    public void agregarPedidoALista(Pedido pedido) {
        String queryPedido = "INSERT INTO Pedido (cliente_id, estado_pedido, precio_total, contexto_pago_id) VALUES (?, ?, ?, ?)";
        String queryContextoPago = "INSERT INTO ContextoPago (estrategia_pago_id) VALUES (?)";
        String queryPago = "INSERT INTO Pago (tipo_estrategia) VALUES (?)";
        String queryEfectivo = "INSERT INTO Efectivo (id) VALUES (?)";
        String queryTransferencia = "INSERT INTO Transferencia (id, cuit, cbu) VALUES (?, ?, ?)";
        String queryMercadoPago = "INSERT INTO MercadoPago (id, alias) VALUES (?, ?)";

        Connection conn = null;
        try {
            conn = DBConnector.getInstance();
            conn.setAutoCommit(false);

            int idPagoGenerado;
            try (PreparedStatement stmtPago = conn.prepareStatement(queryPago, Statement.RETURN_GENERATED_KEYS)) {
                stmtPago.setString(1, pedido.getContextoPago().getPagoStrategy().nombreEstrategia());
                stmtPago.executeUpdate();

                try (ResultSet rsPago = stmtPago.getGeneratedKeys()) {
                    if (rsPago.next()) {
                        idPagoGenerado = rsPago.getInt(1);
                    } else {
                        throw new SQLException("Error al generar el ID de Pago");
                    }
                }
            }

            PagoStrategy pagoStrategy = pedido.getContextoPago().getPagoStrategy();
            if (pagoStrategy instanceof EfectivoStrategy) {
                try (PreparedStatement stmtEfectivo = conn.prepareStatement(queryEfectivo)) {
                    stmtEfectivo.setInt(1, idPagoGenerado);
                    stmtEfectivo.executeUpdate();
                }
            } else if (pagoStrategy instanceof TransferenciaStrategy) {
                TransferenciaStrategy transferencia = (TransferenciaStrategy) pagoStrategy;
                try (PreparedStatement stmtTransferencia = conn.prepareStatement(queryTransferencia)) {
                    stmtTransferencia.setInt(1, idPagoGenerado);
                    stmtTransferencia.setString(2, transferencia.getCuit());
                    stmtTransferencia.setString(3, transferencia.getCbu());
                    stmtTransferencia.executeUpdate();
                }
            } else if (pagoStrategy instanceof MercadoPagoStrategy) {
                MercadoPagoStrategy mercadoPago = (MercadoPagoStrategy) pagoStrategy;
                try (PreparedStatement stmtMercadoPago = conn.prepareStatement(queryMercadoPago)) {
                    stmtMercadoPago.setInt(1, idPagoGenerado);
                    stmtMercadoPago.setString(2, mercadoPago.getAlias());
                    stmtMercadoPago.executeUpdate();
                }
            }

            int idContextoPagoGenerado;
            try (PreparedStatement stmtContextoPago = conn.prepareStatement(queryContextoPago, Statement.RETURN_GENERATED_KEYS)) {
                stmtContextoPago.setInt(1, idPagoGenerado);
                stmtContextoPago.executeUpdate();

                try (ResultSet rsContextoPago = stmtContextoPago.getGeneratedKeys()) {
                    if (rsContextoPago.next()) {
                        idContextoPagoGenerado = rsContextoPago.getInt(1);
                    } else {
                        throw new SQLException("Error al generar el ID de ContextoPago");
                    }
                }
            }

            for (ItemPedido item : pedido.getPedidoDetalle()) {
                itemsPedidoJDBC.agregarItemPedidoALista(item, pedido.getId());
            }

            try (PreparedStatement stmtPedido = conn.prepareStatement(queryPedido, Statement.RETURN_GENERATED_KEYS)) {
                stmtPedido.setInt(1, pedido.getCliente().getId());
                stmtPedido.setString(2, pedido.getEstadoPedido().name());
                stmtPedido.setDouble(3, pedido.getPrecioTotal());
                stmtPedido.setInt(4, idContextoPagoGenerado);
                stmtPedido.executeUpdate();

                try (ResultSet rsPedido = stmtPedido.getGeneratedKeys()) {
                    if (rsPedido.next()) {
                        pedido.setId(rsPedido.getInt(1));
                    }
                }
            }

            conn.commit();
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(PedidoJDBC.class.getName())
                            .log(Level.SEVERE, "Error al realizar rollback", rollbackEx);
                }
            }
            Logger.getLogger(PedidoJDBC.class.getName())
                    .log(Level.SEVERE, "Error al guardar el pedido", ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    Logger.getLogger(PedidoJDBC.class.getName())
                            .log(Level.SEVERE, "Error al cerrar la conexión", closeEx);
                }
            }
        }
    }

    @Override
    public List<Pedido> buscarPedidosPorIdVendedor(Integer idVendedor) {
        String query = "SELECT p.id, p.cliente_id, p.estado_pedido, p.precio_total, p.contexto_pago_id "
                + "FROM Pedido p "
                + "JOIN Cliente c ON p.cliente_id = c.id "
                + "WHERE c.vendedor_id = ?";

        String queryDetalle = "SELECT id, item_menu_id, cantidad, precio "
                + "FROM ItemPedido WHERE pedido_id = ?";

        List<Pedido> pedidos = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnector.getInstance();
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idVendedor);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Pedido pedido = new Pedido();
                        pedido.setId(rs.getInt("id"));
                        int clienteId = rs.getInt("cliente_id");
                        Cliente cliente = clienteJDBC.buscarClientePorId(clienteId);
                        pedido.setCliente(cliente);
                        pedido.setEstadoPedido(EstadoPedidoEnum.valueOf(rs.getString("estado_pedido")));
                        try (PreparedStatement psDetalle = conn.prepareStatement(queryDetalle)) {
                            psDetalle.setInt(1, pedido.getId());
                            try (ResultSet rsDetalle = psDetalle.executeQuery()) {
                                List<ItemPedido> detalles = new ArrayList<>();
                                while (rsDetalle.next()) {
                                    ItemPedido item = new ItemPedido();
                                    item.setId(rsDetalle.getInt("id"));
                                    item.setCantidad(rsDetalle.getInt("cantidad"));
                                    item.setPrecio(rsDetalle.getDouble("precio"));
                                    int itemMenuId = rsDetalle.getInt("item_menu_id");
                                    ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).getFirst();
                                    item.setItemMenu(itemMenu);
                                    detalles.add(item);
                                }
                                pedido.setPedidoDetalle(detalles);
                            }
                        }
                        pedido.setPrecioTotal(rs.getDouble("precio_total"));
                        // Aquí se deberían cargar el cliente, los detalles del pedido, etc.

                        pedidos.add(pedido);
                    }
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName())
                    .log(Level.SEVERE, "Error al buscar pedidos por ID de vendedor", ex);

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(PedidoJDBC.class.getName())
                            .log(Level.SEVERE, "Error al realizar rollback", rollbackEx);
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    Logger.getLogger(PedidoJDBC.class.getName())
                            .log(Level.SEVERE, "Error al cerrar la conexión", closeEx);
                }
            }
        }

        return pedidos;
    }

    @Override
    public List<Pedido> buscarPedidosPorId(Integer idPedido) {
        String queryPedido = "SELECT id, cliente_id, estado_pedido, precio_total, contexto_pago_id "
                + "FROM Pedido WHERE id = ?";
        String queryDetalle = "SELECT id, item_menu_id, cantidad, precio "
                + "FROM ItemPedido WHERE pedido_id = ?";

        List<Pedido> pedidos = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnector.getInstance();
            conn.setAutoCommit(false);
            try (PreparedStatement psPedido = conn.prepareStatement(queryPedido)) {
                psPedido.setInt(1, idPedido);
                try (ResultSet rsPedido = psPedido.executeQuery()) {
                    while (rsPedido.next()) {
                        Pedido pedido = new Pedido();
                        pedido.setId(rsPedido.getInt("id"));
                        pedido.setEstadoPedido(EstadoPedidoEnum.valueOf(rsPedido.getString("estado_pedido")));
                        pedido.setPrecioTotal(rsPedido.getDouble("precio_total"));
                        try (PreparedStatement psDetalle = conn.prepareStatement(queryDetalle)) {
                            psDetalle.setInt(1, pedido.getId());
                            try (ResultSet rsDetalle = psDetalle.executeQuery()) {
                                List<ItemPedido> detalles = new ArrayList<>();
                                while (rsDetalle.next()) {
                                    ItemPedido item = new ItemPedido();
                                    item.setId(rsDetalle.getInt("id"));
                                    item.setCantidad(rsDetalle.getInt("cantidad"));
                                    item.setPrecio(rsDetalle.getDouble("precio"));
                                    int itemMenuId = rsDetalle.getInt("item_menu_id");
                                    ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).getFirst();
                                    item.setItemMenu(itemMenu);
                                    detalles.add(item);
                                }
                                pedido.setPedidoDetalle(detalles);
                            }
                        }
                        pedidos.add(pedido);
                    }
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName())
                    .log(Level.SEVERE, "Error al buscar pedidos por ID de vendedor", ex);

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(PedidoJDBC.class.getName())
                            .log(Level.SEVERE, "Error al realizar rollback", rollbackEx);
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    Logger.getLogger(PedidoJDBC.class.getName())
                            .log(Level.SEVERE, "Error al cerrar la conexión", closeEx);
                }
            }
        }

        return pedidos;
    }

}
