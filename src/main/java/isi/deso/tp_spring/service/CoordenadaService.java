package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.Coordenada;
import isi.deso.tp_spring.domain.Vendedor;
import isi.deso.tp_spring.model.CoordenadaDTO;
import isi.deso.tp_spring.repos.ClienteRepository;
import isi.deso.tp_spring.repos.CoordenadaRepository;
import isi.deso.tp_spring.repos.VendedorRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CoordenadaService {

    private final CoordenadaRepository coordenadaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;

    public CoordenadaService(final CoordenadaRepository coordenadaRepository,
            final ClienteRepository clienteRepository,
            final VendedorRepository vendedorRepository) {
        this.coordenadaRepository = coordenadaRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public List<CoordenadaDTO> findAll() {
        final List<Coordenada> coordenadas = coordenadaRepository.findAll(Sort.by("id"));
        return coordenadas.stream()
                .map(coordenada -> mapToDTO(coordenada, new CoordenadaDTO()))
                .toList();
    }

    public CoordenadaDTO get(final Integer id) {
        return coordenadaRepository.findById(id)
                .map(coordenada -> mapToDTO(coordenada, new CoordenadaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CoordenadaDTO coordenadaDTO) {
        final Coordenada coordenada = new Coordenada();
        mapToEntity(coordenadaDTO, coordenada);
        return coordenadaRepository.save(coordenada).getId();
    }

    public void update(final Integer id, final CoordenadaDTO coordenadaDTO) {
        final Coordenada coordenada = coordenadaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(coordenadaDTO, coordenada);
        coordenadaRepository.save(coordenada);
    }

    public void delete(final Integer id) {
        coordenadaRepository.deleteById(id);
    }

    private CoordenadaDTO mapToDTO(final Coordenada coordenada, final CoordenadaDTO coordenadaDTO) {
        coordenadaDTO.setLat(coordenada.getLat());
        coordenadaDTO.setLgn(coordenada.getLgn());
        return coordenadaDTO;
    }

    private Coordenada mapToEntity(final CoordenadaDTO coordenadaDTO, final Coordenada coordenada) {
        coordenada.setLat(coordenadaDTO.getLat());
        coordenada.setLgn(coordenadaDTO.getLgn());
        return coordenada;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Coordenada coordenada = coordenadaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Cliente coordenadaCliente = clienteRepository.findFirstByCoordenada(coordenada)
                .orElseThrow(NotFoundException::new);
        if (coordenadaCliente != null) {
            referencedWarning.setKey("coordenada.cliente.coordenada.referenced");
            referencedWarning.addParam(coordenadaCliente.getId());
            return referencedWarning;
        }
        final Vendedor coordenadaVendedor = vendedorRepository.findFirstByCoordenada(coordenada);
        if (coordenadaVendedor != null) {
            referencedWarning.setKey("coordenada.vendedor.coordenada.referenced");
            referencedWarning.addParam(coordenadaVendedor.getId());
            return referencedWarning;
        }
        return null;
    }

}
