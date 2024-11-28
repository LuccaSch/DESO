package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Coordenada;
import isi.deso.tp_spring.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    Vendedor findFirstByCoordenada(Coordenada coordenada);

}
