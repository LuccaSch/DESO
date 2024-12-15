package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.ContextoPago;
import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.domain.Pedido;
import isi.deso.tp_spring.model.EstadoPedido;
import isi.deso.tp_spring.model.ItemPedidoDTO;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.repos.ClienteRepository;
import isi.deso.tp_spring.repos.ContextoPagoRepository;
import isi.deso.tp_spring.repos.ItemPedidoRepository;
import isi.deso.tp_spring.repos.PedidoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ContextoPagoRepository contextoPagoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(final PedidoRepository pedidoRepository,
            final ClienteRepository clienteRepository,
            final ContextoPagoRepository contextoPagoRepository,
            final ItemPedidoRepository itemPedidoRepository,
            final ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.contextoPagoRepository = contextoPagoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.itemPedidoService = itemPedidoService;
    }

    public List<PedidoDTO> findAll() {
        final List<Pedido> pedidos = pedidoRepository.findAll(Sort.by("id"));
        return pedidos.stream()
                .map(pedido -> mapToDTO(pedido, new PedidoDTO()))
                .toList();
    }

    public List<ItemPedido> getItemsPedido(Integer idPedido) {
        PedidoDTO pedidoDTO = findAll().stream()
                .filter(p -> p.getId().equals(idPedido))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No se encontró el pedido con id " + idPedido));
        Pedido pedido = null;
        pedido = mapToEntity(pedidoDTO, pedido);
        List<ItemPedido> itemsPedido = pedido.getItemPedidos();
        return itemsPedido;
    }

    public EstadoPedido getEstadoPedido(Integer idPedido) {
        PedidoDTO pedidoDTO = findAll().stream()
                .filter(p -> p.getId().equals(idPedido))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No se encontró el pedido con id " + idPedido));
        Pedido pedido = null;
        pedido = mapToEntity(pedidoDTO, pedido);
        return pedido.getEstadoPedido();
    }

    public List<ItemPedidoDTO> getItemsPedidoDTO(Integer idPedido) {
        PedidoDTO pedidoDTO = findAll().stream()
                .filter(p -> p.getId().equals(idPedido))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No se encontró el pedido con id " + idPedido));
        Pedido pedido = null;
        pedido = mapToEntity(pedidoDTO, pedido);
        List<ItemPedido> itemsPedido = pedido.getItemPedidos();
        List<ItemPedidoDTO> itemsPedidoDTO = new ArrayList<>();

        for (ItemPedido itemPedido : itemsPedido) {
            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            itemPedidoDTO = itemPedidoService.mapToDTO(itemPedido, itemPedidoDTO);
            itemsPedidoDTO.add(itemPedidoDTO);
        }
        return itemsPedidoDTO;
    }

    public PedidoDTO get(final Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> mapToDTO(pedido, new PedidoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public List<PedidoDTO> getByEstadoPedido(final Integer estadoPedido) {
        List<Pedido> pedidos = pedidoRepository.findByEstadoPedido(EstadoPedido.fromInteger(estadoPedido));
        if (pedidos.isEmpty()) {
            throw new NotFoundException();
        }
        return pedidos.stream()
                .map(pedido -> mapToDTO(pedido, new PedidoDTO()))
                .toList();
    }

    public Integer create(final PedidoDTO pedidoDTO) {
        final Pedido pedido = new Pedido();
        mapToEntity(pedidoDTO, pedido);
        return pedidoRepository.save(pedido).getId();
    }

    public void update(final Integer id, final PedidoDTO pedidoDTO) {
        final Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(pedidoDTO, pedido);
        pedidoRepository.save(pedido);
    }

    public void delete(final Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        List<ItemPedido> itemPedidos = pedido.getItemPedidos();

        for (ItemPedido itemPedido : itemPedidos) {
            itemPedido.setPedido(null);
        }

        itemPedidoRepository.saveAll(itemPedidos);

        pedidoRepository.deleteById(id);
    }

    public PedidoDTO mapToDTO(final Pedido pedido, final PedidoDTO pedidoDTO) {
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setEstadoPedido(pedido.getEstadoPedido().toInteger());
        pedidoDTO.setPrecioTotal(pedido.getPrecioTotal());
        pedidoDTO.setCliente(pedido.getCliente() == null ? null : pedido.getCliente().getId());
        pedidoDTO.setContextoPago(pedido.getContextoPago() == null ? null : pedido.getContextoPago().getId());
        return pedidoDTO;
    }

    public Pedido mapToEntity(final PedidoDTO pedidoDTO, final Pedido pedido) {
        pedido.setId(pedidoDTO.getId());
        pedido.setEstadoPedido(EstadoPedido.fromInteger(pedidoDTO.getEstadoPedido()));
        pedido.setPrecioTotal(pedidoDTO.getPrecioTotal());
        final Cliente cliente = pedidoDTO.getCliente() == null ? null
                : clienteRepository.findById(pedidoDTO.getCliente())
                        .orElseThrow(() -> new NotFoundException("cliente not found"));
        pedido.setCliente(cliente);
        final ContextoPago contextoPago = pedidoDTO.getContextoPago() == null ? null
                : contextoPagoRepository.findById(pedidoDTO.getContextoPago())
                        .orElseThrow(() -> new NotFoundException("contextoPago not found"));
        pedido.setContextoPago(contextoPago);
        return pedido;
    }

    public Boolean contextoPagoExists(final Integer id) {
        return pedidoRepository.existsByContextoPagoId(id);
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final ItemPedido pedidoItemPedido = itemPedidoRepository.findFirstByPedido(pedido);
        if (pedidoItemPedido != null) {
            referencedWarning.setKey("pedido.itemPedido.pedido.referenced");
            referencedWarning.addParam(pedidoItemPedido.getId());
            return referencedWarning;
        }
        return null;
    }

}
