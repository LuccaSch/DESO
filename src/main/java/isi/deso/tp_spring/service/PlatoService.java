package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Plato;
import isi.deso.tp_spring.model.PlatoDTO;
import isi.deso.tp_spring.repos.PlatoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PlatoService {

    private final PlatoRepository platoRepository;

    public PlatoService(final PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    public List<PlatoDTO> findAll() {
        final List<Plato> platoes = platoRepository.findAll(Sort.by("id"));
        return platoes.stream()
                .map(plato -> mapToDTO(plato, new PlatoDTO()))
                .toList();
    }

    public PlatoDTO get(final Integer id) {
        return platoRepository.findById(id)
                .map(plato -> mapToDTO(plato, new PlatoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final PlatoDTO platoDTO) {
        final Plato plato = new Plato();
        mapToEntity(platoDTO, plato);
        return platoRepository.save(plato).getId();
    }

    public void update(final Integer id, final PlatoDTO platoDTO) {
        final Plato plato = platoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(platoDTO, plato);
        platoRepository.save(plato);
    }

    public void delete(final Integer id) {
        platoRepository.deleteById(id);
    }

    public PlatoDTO mapToDTO(final Plato plato, final PlatoDTO platoDTO) {
        platoDTO.setNombre(plato.getNombre());
        platoDTO.setDescripcion(plato.getDescripcion());
        platoDTO.setPrecio(plato.getPrecio());
        platoDTO.setPeso(plato.getPeso());
        platoDTO.setAptoCeliaco(plato.getAptoCeliaco());
        platoDTO.setAptoVegano(plato.getAptoVegano());
        platoDTO.setCalorias(plato.getCalorias());
        return platoDTO;
    }

    public Plato mapToEntity(final PlatoDTO platoDTO, final Plato plato) {
        plato.setNombre(platoDTO.getNombre());
        plato.setDescripcion(platoDTO.getDescripcion());
        plato.setPrecio(platoDTO.getPrecio());
        plato.setPeso(platoDTO.getPeso());
        plato.setAptoCeliaco(platoDTO.getAptoCeliaco());
        plato.setAptoVegano(platoDTO.getAptoVegano());
        plato.setCalorias(platoDTO.getCalorias());
        return plato;
    }

}
