package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadaRepository extends JpaRepository<Coordenada, Integer> {
}
