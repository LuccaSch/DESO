package isi.deso.tp_spring.service;

import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.domain.Pedido;
import isi.deso.tp_spring.model.ItemPedidoDTO;
import isi.deso.tp_spring.repos.ItemMenuRepository;
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
    private final ItemMenuRepository itemMenuRepository;

    public ItemPedidoService(final ItemPedidoRepository itemPedidoRepository,
            final PedidoRepository pedidoRepository, final ItemMenuRepository itemMenuRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemMenuRepository = itemMenuRepository;
    }

    public List<ItemPedidoDTO> findAll() {
        final List<ItemPedido> itemPedidos = itemPedidoRepository.findAll(Sort.by("id"));
        return itemPedidos.stream()
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

    public ItemPedidoDTO mapToDTO(final ItemPedido itemPedido, final ItemPedidoDTO itemPedidoDTO) {
        itemPedidoDTO.setCantidad(itemPedido.getCantidad());
        itemPedidoDTO.setPrecio(itemPedido.getPrecio());
        itemPedidoDTO.setPedido(itemPedido.getPedido() == null ? null : itemPedido.getPedido().getId());
        itemPedidoDTO.setItemMenu(itemPedido.getItemMenu() == null ? null : itemPedido.getItemMenu().getId());
        return itemPedidoDTO;
    }

    public ItemPedido mapToEntity(final ItemPedidoDTO itemPedidoDTO, final ItemPedido itemPedido) {
        itemPedido.setCantidad(itemPedidoDTO.getCantidad());
        itemPedido.setPrecio(itemPedidoDTO.getPrecio());
        final Pedido pedido = itemPedidoDTO.getPedido() == null ? null
                : pedidoRepository.findById(itemPedidoDTO.getPedido())
                        .orElseThrow(() -> new NotFoundException("pedido not found"));
        itemPedido.setPedido(pedido);
        final ItemMenu itemMenu = itemPedido.getItemMenu() == null ? null
                : itemMenuRepository.findById(itemPedidoDTO.getItemMenu())
                        .orElseThrow(() -> new NotFoundException("itemMenu not found"));
        itemPedido.setItemMenu(itemMenu);
        return itemPedido;
    }

}
