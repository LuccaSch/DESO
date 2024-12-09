package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.Coordenada;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findFirstByCoordenada(Coordenada coordenada);

    Boolean existsByCuitIgnoreCase(String cuit);

    Optional<Cliente> findByNombre(String nombre);

}
