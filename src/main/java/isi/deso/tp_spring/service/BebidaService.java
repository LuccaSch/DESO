package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Bebida;
import isi.deso.tp_spring.model.BebidaDTO;
import isi.deso.tp_spring.model.Tamano;
import isi.deso.tp_spring.model.TipoBebida;
import isi.deso.tp_spring.repos.BebidaRepository;
import isi.deso.tp_spring.util.NotFoundException;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BebidaService {

    private final BebidaRepository bebidaRepository;

    public BebidaService(final BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    public List<BebidaDTO> findAll() {
        final List<Bebida> bebidas = bebidaRepository.findAll(Sort.by("id"));
        return bebidas.stream()
                .map(bebida -> mapToDTO(bebida, new BebidaDTO()))
                .toList();
    }

    public BebidaDTO get(final Integer id) {
        return bebidaRepository.findById(id)
                .map(bebida -> mapToDTO(bebida, new BebidaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final BebidaDTO bebidaDTO) {
        final Bebida bebida = new Bebida();
        mapToEntity(bebidaDTO, bebida);
        return bebidaRepository.save(bebida).getId();
    }

    public void update(final Integer id, final BebidaDTO bebidaDTO) {
        final Bebida bebida = bebidaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bebidaDTO, bebida);
        bebidaRepository.save(bebida);
    }

    public void delete(final Integer id) {
        bebidaRepository.deleteById(id);
    }

    public BebidaDTO mapToDTO(final Bebida bebida, final BebidaDTO bebidaDTO) {
        bebidaDTO.setNombre(bebida.getNombre());
        bebidaDTO.setDescripcion(bebida.getDescripcion());
        bebidaDTO.setPrecio(bebida.getPrecio());
        bebidaDTO.setPeso(bebida.getPeso());
        bebidaDTO.setVolumen(bebida.getVolumen());
        bebidaDTO.setGraduacionAlcoholica(bebida.getGraduacionAlcoholica());
        bebidaDTO.setTipoBebida(bebida.getTipoBebida().toInteger());
        bebidaDTO.setTamano(bebida.getTamano().toInteger());
        return bebidaDTO;
    }

    public Bebida mapToEntity(final BebidaDTO bebidaDTO, final Bebida bebida) {
        bebida.setNombre(bebidaDTO.getNombre());
        bebida.setDescripcion(bebidaDTO.getDescripcion());
        bebida.setPrecio(bebidaDTO.getPrecio());
        bebida.setPeso(bebidaDTO.getPeso());
        bebida.setVolumen(bebidaDTO.getVolumen());
        bebida.setGraduacionAlcoholica(bebidaDTO.getGraduacionAlcoholica());
        bebida.setTipoBebida(TipoBebida.fromInteger(bebidaDTO.getTipoBebida()));
        bebida.setTamano(Tamano.fromInteger(bebidaDTO.getTamano()));
        return bebida;
    }

}
