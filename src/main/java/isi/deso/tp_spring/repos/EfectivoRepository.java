package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Efectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EfectivoRepository extends JpaRepository<Efectivo, Integer> {
}
