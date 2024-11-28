package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.domain.Pedido;
import isi.deso.tp_spring.model.ItemPedidoDTO;
import isi.deso.tp_spring.repos.ItemPedidoRepository;
import isi.deso.tp_spring.repos.PedidoRepository;
import isi.deso.tp_spring.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;

    public ItemPedidoService(final ItemPedidoRepository itemPedidoRepository,
            final PedidoRepository pedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<ItemPedidoDTO> findAll() {
        final List<ItemPedido> itemPedidoes = itemPedidoRepository.findAll(Sort.by("id"));
        return itemPedidoes.stream()
                .map(itemPedido -> mapToDTO(itemPedido, new ItemPedidoDTO()))
                .toList();
    }

    public ItemPedidoDTO get(final Integer id) {
        return itemPedidoRepository.findById(id)
                .map(itemPedido -> mapToDTO(itemPedido, new ItemPedidoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ItemPedidoDTO itemPedidoDTO) {
        final ItemPedido itemPedido = new ItemPedido();
        mapToEntity(itemPedidoDTO, itemPedido);
        return itemPedidoRepository.save(itemPedido).getId();
    }

    public void update(final Integer id, final ItemPedidoDTO itemPedidoDTO) {
        final ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(itemPedidoDTO, itemPedido);
        itemPedidoRepository.save(itemPedido);
    }

    public void delete(final Integer id) {
        itemPedidoRepository.deleteById(id);
    }

    private ItemPedidoDTO mapToDTO(final ItemPedido itemPedido, final ItemPedidoDTO itemPedidoDTO) {
        itemPedidoDTO.setId(itemPedido.getId());
        itemPedidoDTO.setCantidad(itemPedido.getCantidad());
        itemPedidoDTO.setPrecio(itemPedido.getPrecio());
        itemPedidoDTO.setPedido(itemPedido.getPedido() == null ? null : itemPedido.getPedido().getId());
        return itemPedidoDTO;
    }

    private ItemPedido mapToEntity(final ItemPedidoDTO itemPedidoDTO, final ItemPedido itemPedido) {
        itemPedido.setCantidad(itemPedidoDTO.getCantidad());
        itemPedido.setPrecio(itemPedidoDTO.getPrecio());
        final Pedido pedido = itemPedidoDTO.getPedido() == null ? null : pedidoRepository.findById(itemPedidoDTO.getPedido())
                .orElseThrow(() -> new NotFoundException("pedido not found"));
        itemPedido.setPedido(pedido);
        return itemPedido;
    }

}
