package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.ContextoPago;
import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.domain.Pedido;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.repos.ClienteRepository;
import isi.deso.tp_spring.repos.ContextoPagoRepository;
import isi.deso.tp_spring.repos.ItemPedidoRepository;
import isi.deso.tp_spring.repos.PedidoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ContextoPagoRepository contextoPagoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(final PedidoRepository pedidoRepository,
            final ClienteRepository clienteRepository,
            final ContextoPagoRepository contextoPagoRepository,
            final ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.contextoPagoRepository = contextoPagoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<PedidoDTO> findAll() {
        final List<Pedido> pedidoes = pedidoRepository.findAll(Sort.by("id"));
        return pedidoes.stream()
                .map(pedido -> mapToDTO(pedido, new PedidoDTO()))
                .toList();
    }

    public PedidoDTO get(final Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> mapToDTO(pedido, new PedidoDTO()))
                .orElseThrow(NotFoundException::new);
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
        pedidoRepository.deleteById(id);
    }

    private PedidoDTO mapToDTO(final Pedido pedido, final PedidoDTO pedidoDTO) {
        pedidoDTO.setEstadoPedido(pedido.getEstadoPedido());
        pedidoDTO.setPrecioTotal(pedido.getPrecioTotal());
        pedidoDTO.setCliente(pedido.getCliente() == null ? null : pedido.getCliente().getId());
        pedidoDTO.setContextoPago(pedido.getContextoPago() == null ? null : pedido.getContextoPago().getId());
        return pedidoDTO;
    }

    private Pedido mapToEntity(final PedidoDTO pedidoDTO, final Pedido pedido) {
        pedido.setEstadoPedido(pedidoDTO.getEstadoPedido());
        pedido.setPrecioTotal(pedidoDTO.getPrecioTotal());
        final Cliente cliente = pedidoDTO.getCliente() == null ? null : clienteRepository.findById(pedidoDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));
        pedido.setCliente(cliente);
        final ContextoPago contextoPago = pedidoDTO.getContextoPago() == null ? null : contextoPagoRepository.findById(pedidoDTO.getContextoPago())
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
