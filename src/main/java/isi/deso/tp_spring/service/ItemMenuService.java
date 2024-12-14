package isi.deso.tp_spring.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import isi.deso.tp_spring.domain.Categoria;
import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.Vendedor;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.repos.CategoriaRepository;
import isi.deso.tp_spring.repos.ItemMenuRepository;
import isi.deso.tp_spring.repos.VendedorRepository;
import isi.deso.tp_spring.util.NotFoundException;

@Service
public class ItemMenuService {

    private final ItemMenuRepository itemMenuRepository;
    private final CategoriaRepository categoriaRepository;
    private final VendedorRepository vendedorRepository;

    public ItemMenuService(final ItemMenuRepository itemMenuRepository,
            final CategoriaRepository categoriaRepository,
            final VendedorRepository vendedorRepository) {
        this.itemMenuRepository = itemMenuRepository;
        this.categoriaRepository = categoriaRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public List<ItemMenuDTO> findAll() {
        final List<ItemMenu> ItemMenus = itemMenuRepository.findAll(Sort.by("id"));
        return ItemMenus.stream()
                .map(ItemMenu -> mapToDTO(ItemMenu, new ItemMenuDTO()))
                .toList();
    }

    public ItemMenuDTO get(final Integer id) {
        return itemMenuRepository.findById(id)
                .map(ItemMenu -> mapToDTO(ItemMenu, new ItemMenuDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public ItemMenuDTO getByPrecio(final Double precio) {
        return itemMenuRepository.findByPrecio(precio)
                .map(ItemMenu -> mapToDTO(ItemMenu, new ItemMenuDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public void update(final Integer id, final ItemMenuDTO ItemMenuDTO) {
        final ItemMenu ItemMenu = itemMenuRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ItemMenuDTO, ItemMenu);
        itemMenuRepository.save(ItemMenu);
    }

    public void delete(final Integer id) {

        // TODO: completar. Setear a null en las relaciones
        itemMenuRepository.deleteById(id);
    }

    public List<ItemMenuDTO> findByIdVendedor(Integer idVendedor) {
        Vendedor vendedor = vendedorRepository.findById(idVendedor).orElseThrow(NotFoundException::new);
        return itemMenuRepository.findByVendedor(vendedor);
    }

    // public List<ItemMenuDTO> getItemsDeVendedor(Integer idVendedor) {
    // List<ItemMenuDTO> items = findAll();
    // return items.stream().filter(item ->
    // item.getVendedor().equals(idVendedor)).toList();
    // }

    public ItemMenuDTO mapToDTO(final ItemMenu ItemMenu, final ItemMenuDTO ItemMenuDTO) {
        ItemMenuDTO.setNombre(ItemMenu.getNombre());
        ItemMenuDTO.setDescripcion(ItemMenu.getDescripcion());
        ItemMenuDTO.setPrecio(ItemMenu.getPrecio());
        ItemMenuDTO.setPeso(ItemMenu.getPeso());
        ItemMenuDTO.setCategoria(ItemMenu.getCategoria() == null ? null : ItemMenu.getCategoria().getId());
        ItemMenuDTO.setVendedor(ItemMenu.getVendedor() == null ? null : ItemMenu.getVendedor().getId());
        return ItemMenuDTO;
    }

    public ItemMenu mapToEntity(final ItemMenuDTO ItemMenuDTO, final ItemMenu ItemMenu) {
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
