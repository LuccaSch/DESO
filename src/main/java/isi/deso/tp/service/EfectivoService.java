package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Efectivo;
import isi.deso.tp_spring.model.EfectivoDTO;
import isi.deso.tp_spring.repos.EfectivoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EfectivoService {

    private final EfectivoRepository efectivoRepository;

    public EfectivoService(final EfectivoRepository efectivoRepository) {
        this.efectivoRepository = efectivoRepository;
    }

    public List<EfectivoDTO> findAll() {
        final List<Efectivo> efectivoes = efectivoRepository.findAll(Sort.by("id"));
        return efectivoes.stream()
                .map(efectivo -> mapToDTO(efectivo, new EfectivoDTO()))
                .toList();
    }

    public EfectivoDTO get(final Integer id) {
        return efectivoRepository.findById(id)
                .map(efectivo -> mapToDTO(efectivo, new EfectivoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EfectivoDTO efectivoDTO) {
        final Efectivo efectivo = new Efectivo();
        mapToEntity(efectivoDTO, efectivo);
        return efectivoRepository.save(efectivo).getId();
    }

    public void update(final Integer id, final EfectivoDTO efectivoDTO) {
        final Efectivo efectivo = efectivoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(efectivoDTO, efectivo);
        efectivoRepository.save(efectivo);
    }

    public void delete(final Integer id) {
        efectivoRepository.deleteById(id);
    }

    private EfectivoDTO mapToDTO(final Efectivo efectivo, final EfectivoDTO efectivoDTO) {
        efectivoDTO.setId(efectivo.getId());
        return efectivoDTO;
    }

    private Efectivo mapToEntity(final EfectivoDTO efectivoDTO, final Efectivo efectivo) {
        return efectivo;
    }

}
