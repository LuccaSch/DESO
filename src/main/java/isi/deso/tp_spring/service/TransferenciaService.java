package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Transferencia;
import isi.deso.tp_spring.model.TransferenciaDTO;
import isi.deso.tp_spring.repos.TransferenciaRepository;
import isi.deso.tp_spring.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(final TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public List<TransferenciaDTO> findAll() {
        final List<Transferencia> transferencias = transferenciaRepository.findAll(Sort.by("id"));
        return transferencias.stream()
                .map(transferencia -> mapToDTO(transferencia, new TransferenciaDTO()))
                .toList();
    }

    public TransferenciaDTO get(final Integer id) {
        return transferenciaRepository.findById(id)
                .map(transferencia -> mapToDTO(transferencia, new TransferenciaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final TransferenciaDTO transferenciaDTO) {
        final Transferencia transferencia = new Transferencia();
        mapToEntity(transferenciaDTO, transferencia);
        return transferenciaRepository.save(transferencia).getId();
    }

    public void update(final Integer id, final TransferenciaDTO transferenciaDTO) {
        final Transferencia transferencia = transferenciaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(transferenciaDTO, transferencia);
        transferenciaRepository.save(transferencia);
    }

    public void delete(final Integer id) {
        transferenciaRepository.deleteById(id);
    }

    private TransferenciaDTO mapToDTO(final Transferencia transferencia,
            final TransferenciaDTO transferenciaDTO) {
        transferenciaDTO.setCuit(transferencia.getCuit());
        transferenciaDTO.setCbu(transferencia.getCbu());
        return transferenciaDTO;
    }

    private Transferencia mapToEntity(final TransferenciaDTO transferenciaDTO,
            final Transferencia transferencia) {
        transferencia.setCuit(transferenciaDTO.getCuit());
        transferencia.setCbu(transferenciaDTO.getCbu());
        return transferencia;
    }

}
