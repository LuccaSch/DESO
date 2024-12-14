package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.Coordenada;
import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.Pedido;
import isi.deso.tp_spring.domain.Vendedor;
import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.repos.CoordenadaRepository;
import isi.deso.tp_spring.repos.ItemMenuRepository;
import isi.deso.tp_spring.repos.VendedorRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final CoordenadaRepository coordenadaRepository;
    private final ItemMenuRepository ItemMenuRepository;

    public VendedorService(final VendedorRepository vendedorRepository,
            final CoordenadaRepository coordenadaRepository,
            final ItemMenuRepository ItemMenuRepository) {
        this.vendedorRepository = vendedorRepository;
        this.coordenadaRepository = coordenadaRepository;
        this.ItemMenuRepository = ItemMenuRepository;
    }

    public List<ItemMenu> obtenerItemsMenuDelVendedor(Vendedor vendedor) {
        return ItemMenuRepository.findAll().stream()
                .filter(item -> ItemMenuRepository.findFirstByVendedor(vendedor).equals(item))
                .collect(Collectors.toList());
    }

    public List<VendedorDTO> findAll() {
        final List<Vendedor> vendedors = vendedorRepository.findAll(Sort.by("id"));
        return vendedors.stream()
                .map(vendedor -> mapToDTO(vendedor, new VendedorDTO()))
                .toList();
    }

    public VendedorDTO get(final Integer id) {
        return vendedorRepository.findById(id)
                .map(vendedor -> mapToDTO(vendedor, new VendedorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public List<VendedorDTO> getByNombre(final String nombre) {
        return vendedorRepository.findByNombreStartingWithIgnoreCase(nombre)
                .stream()
                .map(vendedor -> mapToDTO(vendedor, new VendedorDTO()))
                .toList();
    }

    public Integer create(final VendedorDTO vendedorDTO) {
        final Vendedor vendedor = new Vendedor();
        mapToEntity(vendedorDTO, vendedor);
        return vendedorRepository.save(vendedor).getId();
    }

    public void update(final Integer id, final VendedorDTO vendedorDTO) {
        final Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(vendedorDTO, vendedor);
        vendedorRepository.save(vendedor);
    }

    public void delete(final Integer id) {

        vendedorRepository.deleteById(id);
    }

    public VendedorDTO mapToDTO(final Vendedor vendedor, final VendedorDTO vendedorDTO) {
        vendedorDTO.setId(vendedor.getId());
        vendedorDTO.setNombre(vendedor.getNombre());
        vendedorDTO.setDireccion(vendedor.getDireccion());
        vendedorDTO.setCoordenada(vendedor.getCoordenada() == null ? null : vendedor.getCoordenada().getId());
        return vendedorDTO;
    }

    public Vendedor mapToEntity(final VendedorDTO vendedorDTO, final Vendedor vendedor) {
        vendedor.setId(vendedorDTO.getId());
        vendedor.setNombre(vendedorDTO.getNombre());
        vendedor.setDireccion(vendedorDTO.getDireccion());
        final Coordenada coordenada = vendedorDTO.getCoordenada() == null ? null
                : coordenadaRepository.findById(vendedorDTO.getCoordenada())
                        .orElseThrow(() -> new NotFoundException("coordenada not found"));
        vendedor.setCoordenada(coordenada);
        return vendedor;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final ItemMenu vendedorItemMenu = ItemMenuRepository.findFirstByVendedor(vendedor);
        if (vendedorItemMenu != null) {
            referencedWarning.setKey("vendedor.ItemMenu.vendedor.referenced");
            referencedWarning.addParam(vendedorItemMenu.getId());
            return referencedWarning;
        }
        return null;
    }

}
