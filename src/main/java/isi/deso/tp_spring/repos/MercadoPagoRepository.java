package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.MercadoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoPagoRepository extends JpaRepository<MercadoPago, Integer> {
}
