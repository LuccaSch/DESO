package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
}
