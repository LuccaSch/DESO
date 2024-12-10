package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.ContextoPago;
import isi.deso.tp_spring.domain.Pago;
import isi.deso.tp_spring.domain.Pedido;
import isi.deso.tp_spring.model.ContextoPagoDTO;
import isi.deso.tp_spring.repos.ContextoPagoRepository;
import isi.deso.tp_spring.repos.PagoRepository;
import isi.deso.tp_spring.repos.PedidoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContextoPagoService {

    private final ContextoPagoRepository contextoPagoRepository;
    private final PagoRepository pagoRepository;
    private final PedidoRepository pedidoRepository;

    public ContextoPagoService(final ContextoPagoRepository contextoPagoRepository,
            final PagoRepository pagoRepository, final PedidoRepository pedidoRepository) {
        this.contextoPagoRepository = contextoPagoRepository;
        this.pagoRepository = pagoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<ContextoPagoDTO> findAll() {
        final List<ContextoPago> contextoPagoes = contextoPagoRepository.findAll(Sort.by("id"));
        return contextoPagoes.stream()
                .map(contextoPago -> mapToDTO(contextoPago, new ContextoPagoDTO()))
                .toList();
    }

    public ContextoPagoDTO get(final Integer id) {
        return contextoPagoRepository.findById(id)
                .map(contextoPago -> mapToDTO(contextoPago, new ContextoPagoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ContextoPagoDTO contextoPagoDTO) {
        final ContextoPago contextoPago = new ContextoPago();
        mapToEntity(contextoPagoDTO, contextoPago);
        return contextoPagoRepository.save(contextoPago).getId();
    }

    public void update(final Integer id, final ContextoPagoDTO contextoPagoDTO) {
        final ContextoPago contextoPago = contextoPagoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(contextoPagoDTO, contextoPago);
        contextoPagoRepository.save(contextoPago);
    }

    public void delete(final Integer id) {
        contextoPagoRepository.deleteById(id);
    }

    private ContextoPagoDTO mapToDTO(final ContextoPago contextoPago,
            final ContextoPagoDTO contextoPagoDTO) {
        contextoPagoDTO.setPago(contextoPago.getPago() == null ? null : contextoPago.getPago().getId());
        return contextoPagoDTO;
    }

    private ContextoPago mapToEntity(final ContextoPagoDTO contextoPagoDTO,
            final ContextoPago contextoPago) {
        final Pago pago = contextoPagoDTO.getPago() == null ? null : pagoRepository.findById(contextoPagoDTO.getPago())
                .orElseThrow(() -> new NotFoundException("pago not found"));
        contextoPago.setPago(pago);
        return contextoPago;
    }

    public Boolean pagoExists(final Integer id) {
        return contextoPagoRepository.existsByPagoId(id);
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final ContextoPago contextoPago = contextoPagoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Pedido contextoPagoPedido = pedidoRepository.findFirstByContextoPago(contextoPago);
        if (contextoPagoPedido != null) {
            referencedWarning.setKey("contextoPago.pedido.contextoPago.referenced");
            referencedWarning.addParam(contextoPagoPedido.getId());
            return referencedWarning;
        }
        return null;
    }

}
