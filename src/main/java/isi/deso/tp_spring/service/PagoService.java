package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.ContextoPago;
import isi.deso.tp_spring.domain.Pago;
import isi.deso.tp_spring.model.PagoDTO;
import isi.deso.tp_spring.model.TipoEstrategia;
import isi.deso.tp_spring.repos.ContextoPagoRepository;
import isi.deso.tp_spring.repos.PagoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ContextoPagoRepository contextoPagoRepository;

    public PagoService(final PagoRepository pagoRepository,
            final ContextoPagoRepository contextoPagoRepository) {
        this.pagoRepository = pagoRepository;
        this.contextoPagoRepository = contextoPagoRepository;
    }

    public List<PagoDTO> findAll() {
        final List<Pago> pagos = pagoRepository.findAll(Sort.by("id"));
        return pagos.stream()
                .map(pago -> mapToDTO(pago, new PagoDTO()))
                .toList();
    }

    public PagoDTO get(final Integer id) {
        return pagoRepository.findById(id)
                .map(pago -> mapToDTO(pago, new PagoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final PagoDTO pagoDTO) {
        final Pago pago = new Pago();
        mapToEntity(pagoDTO, pago);
        return pagoRepository.save(pago).getId();
    }

    public void update(final Integer id, final PagoDTO pagoDTO) {
        final Pago pago = pagoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(pagoDTO, pago);
        pagoRepository.save(pago);
    }

    public void delete(final Integer id) {
        pagoRepository.deleteById(id);
    }

    public PagoDTO mapToDTO(final Pago pago, final PagoDTO pagoDTO) {

        return pagoDTO;
    }

    public Pago mapToEntity(final PagoDTO pagoDTO, final Pago pago) {

        return pago;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Pago pago = pagoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final ContextoPago pagoContextoPago = contextoPagoRepository.findFirstByPago(pago);
        if (pagoContextoPago != null) {
            referencedWarning.setKey("pago.contextoPago.pago.referenced");
            referencedWarning.addParam(pagoContextoPago.getId());
            return referencedWarning;
        }
        return null;
    }

}
