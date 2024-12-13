package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Categoria;
import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.model.CategoriaDTO;
import isi.deso.tp_spring.repos.CategoriaRepository;
import isi.deso.tp_spring.repos.ItemMenuRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ItemMenuRepository ItemMenuRepository;

    public CategoriaService(final CategoriaRepository categoriaRepository,
            final ItemMenuRepository ItemMenuRepository) {
        this.categoriaRepository = categoriaRepository;
        this.ItemMenuRepository = ItemMenuRepository;
    }

    public List<CategoriaDTO> findAll() {
        final List<Categoria> categorias = categoriaRepository.findAll(Sort.by("id"));
        return categorias.stream()
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .toList();
    }

    public CategoriaDTO get(final Integer id) {
        return categoriaRepository.findById(id)
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CategoriaDTO categoriaDTO) {
        final Categoria categoria = new Categoria();
        mapToEntity(categoriaDTO, categoria);
        return categoriaRepository.save(categoria).getId();
    }

    public void update(final Integer id, final CategoriaDTO categoriaDTO) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoriaDTO, categoria);
        categoriaRepository.save(categoria);
    }

    public void delete(final Integer id) {
        categoriaRepository.deleteById(id);
    }

    public CategoriaDTO mapToDTO(final Categoria categoria, final CategoriaDTO categoriaDTO) {
        categoriaDTO.setDescripcion(categoria.getDescripcion());
        return categoriaDTO;
    }

    public Categoria mapToEntity(final CategoriaDTO categoriaDTO, final Categoria categoria) {
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        return categoria;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final ItemMenu categoriaItemMenu = ItemMenuRepository.findFirstByCategoria(categoria);
        if (categoriaItemMenu != null) {
            referencedWarning.setKey("categoria.ItemMenu.categoria.referenced");
            referencedWarning.addParam(categoriaItemMenu.getId());
            return referencedWarning;
        }
        return null;
    }

}
