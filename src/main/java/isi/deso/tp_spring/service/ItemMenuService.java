package isi.deso.tp_spring.service;

import java.util.List;

import isi.deso.tp_spring.repos.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import isi.deso.tp_spring.domain.Categoria;
import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.Vendedor;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.util.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemMenuService {

    private final ItemMenuRepository itemMenuRepository;
    private final BebidaRepository bebidaRepository;
    private final PlatoRepository platoRepository;
    private final CategoriaRepository categoriaRepository;
    private final VendedorRepository vendedorRepository;

    public ItemMenuService(final ItemMenuRepository itemMenuRepository, BebidaRepository bebidaRepository, PlatoRepository platoRepository,
                           final CategoriaRepository categoriaRepository,
                           final VendedorRepository vendedorRepository) {
        this.itemMenuRepository = itemMenuRepository;
        this.bebidaRepository = bebidaRepository;
        this.platoRepository = platoRepository;
        this.categoriaRepository = categoriaRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public List<ItemMenuDTO> findAll() {
        return itemMenuRepository.findAll(Sort.by("id")).stream()
                .map(item -> mapToDTO(item, new ItemMenuDTO()))
                .toList();
    }

    public ItemMenuDTO get(final Integer id) {
        return itemMenuRepository.findById(id)
                .map(item -> mapToDTO(item, new ItemMenuDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public List<ItemMenuDTO> getByPrecio(final Double precio) {
        List<ItemMenu> items = itemMenuRepository.findByPrecio(precio);
        if (items.isEmpty()) {
            throw new NotFoundException();
        }
        return items.stream()
                .map(item-> mapToDTO(item, new ItemMenuDTO()))
                .toList();
    }

    public void update(final Integer id, final ItemMenuDTO ItemMenuDTO) {
        final ItemMenu ItemMenu = itemMenuRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ItemMenuDTO, ItemMenu);
        itemMenuRepository.save(ItemMenu);
    }

    public void delete(final Integer id) {

        itemMenuRepository.deleteById(id);
    }

    public List<ItemMenuDTO> findByIdVendedor(Integer idVendedor) {
        Vendedor vendedor = vendedorRepository.findById(idVendedor).orElseThrow(NotFoundException::new);
        return itemMenuRepository.findByVendedor(vendedor)
                .stream()
                .map(itemMenu -> mapToDTO(itemMenu, new ItemMenuDTO()))
                .toList();
    }


    public ItemMenuDTO mapToDTO(final ItemMenu ItemMenu, final ItemMenuDTO ItemMenuDTO) {
        ItemMenuDTO.setId(ItemMenu.getId());
        ItemMenuDTO.setNombre(ItemMenu.getNombre());
        ItemMenuDTO.setDescripcion(ItemMenu.getDescripcion());
        ItemMenuDTO.setPrecio(ItemMenu.getPrecio());
        ItemMenuDTO.setPeso(ItemMenu.getPeso());
        ItemMenuDTO.setCategoria(ItemMenu.getCategoria() == null ? null : ItemMenu.getCategoria().getId());
        ItemMenuDTO.setVendedor(ItemMenu.getVendedor() == null ? null : ItemMenu.getVendedor().getId());
        return ItemMenuDTO;
    }

    public ItemMenu mapToEntity(final ItemMenuDTO ItemMenuDTO, final ItemMenu ItemMenu) {
        ItemMenu.setId(ItemMenuDTO.getId());
        ItemMenu.setNombre(ItemMenuDTO.getNombre());
        ItemMenu.setDescripcion(ItemMenuDTO.getDescripcion());
        ItemMenu.setPrecio(ItemMenuDTO.getPrecio());
        ItemMenu.setPeso(ItemMenuDTO.getPeso());
        final Categoria categoria = ItemMenuDTO.getCategoria() == null ? null
                : categoriaRepository.findById(ItemMenuDTO.getCategoria())
                        .orElseThrow(() -> new NotFoundException("categoria not found"));
        ItemMenu.setCategoria(categoria);
        final Vendedor vendedor = ItemMenuDTO.getVendedor() == null ? null
                : vendedorRepository.findById(ItemMenuDTO.getVendedor())
                        .orElseThrow(() -> new NotFoundException("vendedor not found"));
        ItemMenu.setVendedor(vendedor);
        return ItemMenu;
    }

    public Integer getIdVendedor(ItemMenu itemMenu) {
        return itemMenu.getVendedor().getId();
    }

}
