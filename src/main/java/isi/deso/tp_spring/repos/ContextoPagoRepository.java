package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.ContextoPago;
import isi.deso.tp_spring.domain.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextoPagoRepository extends JpaRepository<ContextoPago, Integer> {

    ContextoPago findFirstByPago(Pago pago);

    Boolean existsByPagoId(Integer id);

}
