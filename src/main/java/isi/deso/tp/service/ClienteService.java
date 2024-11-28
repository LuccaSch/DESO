package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.Coordenada;
import isi.deso.tp_spring.domain.Pedido;
import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.repos.ClienteRepository;
import isi.deso.tp_spring.repos.CoordenadaRepository;
import isi.deso.tp_spring.repos.PedidoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CoordenadaRepository coordenadaRepository;
    private final PedidoRepository pedidoRepository;

    public ClienteService(final ClienteRepository clienteRepository,
            final CoordenadaRepository coordenadaRepository,
            final PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.coordenadaRepository = coordenadaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<ClienteDTO> findAll() {
        final List<Cliente> clientes = clienteRepository.findAll(Sort.by("id"));
        return clientes.stream()
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .toList();
    }

    public ClienteDTO get(final Integer id) {
        return clienteRepository.findById(id)
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ClienteDTO clienteDTO) {
        final Cliente cliente = new Cliente();
        mapToEntity(clienteDTO, cliente);
        return clienteRepository.save(cliente).getId();
    }

    public void update(final Integer id, final ClienteDTO clienteDTO) {
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clienteDTO, cliente);
        clienteRepository.save(cliente);
    }

    public void delete(final Integer id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO mapToDTO(final Cliente cliente, final ClienteDTO clienteDTO) {
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setCuit(cliente.getCuit());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setCoordenada(cliente.getCoordenada() == null ? null : cliente.getCoordenada().getId());
        return clienteDTO;
    }

    private Cliente mapToEntity(final ClienteDTO clienteDTO, final Cliente cliente) {
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setCuit(clienteDTO.getCuit());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDireccion(clienteDTO.getDireccion());
        final Coordenada coordenada = clienteDTO.getCoordenada() == null ? null : coordenadaRepository.findById(clienteDTO.getCoordenada())
                .orElseThrow(() -> new NotFoundException("coordenada not found"));
        cliente.setCoordenada(coordenada);
        return cliente;
    }

    public boolean cuitExists(final String cuit) {
        return clienteRepository.existsByCuitIgnoreCase(cuit);
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Pedido clientePedido = pedidoRepository.findFirstByCliente(cliente);
        if (clientePedido != null) {
            referencedWarning.setKey("cliente.pedido.cliente.referenced");
            referencedWarning.addParam(clientePedido.getId());
            return referencedWarning;
        }
        return null;
    }

}
