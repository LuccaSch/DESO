package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    ItemPedido findFirstByPedido(Pedido pedido);

}
