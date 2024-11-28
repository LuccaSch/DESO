package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoordenadaRepository extends JpaRepository<Coordenada, Integer> {
}
