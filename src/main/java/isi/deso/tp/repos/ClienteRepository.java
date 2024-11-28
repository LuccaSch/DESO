package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Cliente;
import isi.deso.tp_spring.domain.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findFirstByCoordenada(Coordenada coordenada);

    boolean existsByCuitIgnoreCase(String cuit);

}
