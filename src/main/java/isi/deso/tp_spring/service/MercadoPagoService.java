package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.MercadoPago;
import isi.deso.tp_spring.model.MercadoPagoDTO;
import isi.deso.tp_spring.repos.MercadoPagoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService {

    private final MercadoPagoRepository mercadoPagoRepository;

    public MercadoPagoService(final MercadoPagoRepository mercadoPagoRepository) {
        this.mercadoPagoRepository = mercadoPagoRepository;
    }

    public List<MercadoPagoDTO> findAll() {
        final List<MercadoPago> mercadoPagoes = mercadoPagoRepository.findAll(Sort.by("id"));
        return mercadoPagoes.stream()
                .map(mercadoPago -> mapToDTO(mercadoPago, new MercadoPagoDTO()))
                .toList();
    }

    public MercadoPagoDTO get(final Integer id) {
        return mercadoPagoRepository.findById(id)
                .map(mercadoPago -> mapToDTO(mercadoPago, new MercadoPagoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final MercadoPagoDTO mercadoPagoDTO) {
        final MercadoPago mercadoPago = new MercadoPago();
        mapToEntity(mercadoPagoDTO, mercadoPago);
        return mercadoPagoRepository.save(mercadoPago).getId();
    }

    public void update(final Integer id, final MercadoPagoDTO mercadoPagoDTO) {
        final MercadoPago mercadoPago = mercadoPagoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(mercadoPagoDTO, mercadoPago);
        mercadoPagoRepository.save(mercadoPago);
    }

    public void delete(final Integer id) {
        mercadoPagoRepository.deleteById(id);
    }

    private MercadoPagoDTO mapToDTO(final MercadoPago mercadoPago,
            final MercadoPagoDTO mercadoPagoDTO) {
        mercadoPagoDTO.setAlias(mercadoPago.getAlias());
        return mercadoPagoDTO;
    }

    private MercadoPago mapToEntity(final MercadoPagoDTO mercadoPagoDTO,
            final MercadoPago mercadoPago) {
        mercadoPago.setAlias(mercadoPagoDTO.getAlias());
        return mercadoPago;
    }

}
