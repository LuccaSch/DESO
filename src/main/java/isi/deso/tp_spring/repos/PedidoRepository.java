package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.ContextoPago;
import isi.deso.tp_spring.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Pedido findFirstByCliente(Cliente cliente);

    Pedido findFirstByContextoPago(ContextoPago contextoPago);

    boolean existsByContextoPagoId(Integer id);

}
