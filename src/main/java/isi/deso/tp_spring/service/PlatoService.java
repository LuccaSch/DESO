package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Categoria;
import isi.deso.tp_spring.domain.Plato;
import isi.deso.tp_spring.domain.Vendedor;
import isi.deso.tp_spring.model.PlatoDTO;
import isi.deso.tp_spring.repos.BebidaRepository;
import isi.deso.tp_spring.repos.CategoriaRepository;
import isi.deso.tp_spring.repos.PlatoRepository;
import isi.deso.tp_spring.repos.VendedorRepository;
import isi.deso.tp_spring.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlatoService {

    private final PlatoRepository platoRepository;
    private final CategoriaRepository categoriaRepository;
    private final VendedorRepository vendedorRepository;

    public PlatoService(final PlatoRepository platoRepository, final CategoriaRepository categoriaRepository,
            final VendedorRepository vendedorRepository) {
        this.platoRepository = platoRepository;
        this.categoriaRepository = categoriaRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public List<PlatoDTO> findAll() {
        final List<Plato> platos = platoRepository.findAll(Sort.by("id"));
        return platos.stream()
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
        platoDTO.setCategoria(plato.getCategoria().getId());
        platoDTO.setVendedor(plato.getVendedor().getId());
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
        final Categoria categoria = categoriaRepository.findById(platoDTO.getCategoria())
                .orElseThrow(() -> new NotFoundException("categoria not found"));
        plato.setCategoria(categoria);
        final Vendedor vendedor = vendedorRepository.findById(platoDTO.getVendedor())
                .orElseThrow(() -> new NotFoundException("vendedor not found"));
        plato.setVendedor(vendedor);
        plato.setAptoCeliaco(platoDTO.getAptoCeliaco());
        plato.setAptoVegano(platoDTO.getAptoVegano());
        plato.setCalorias(platoDTO.getCalorias());
        return plato;
    }

}
