package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BebidaRepository extends JpaRepository<Bebida, Integer> {
}
