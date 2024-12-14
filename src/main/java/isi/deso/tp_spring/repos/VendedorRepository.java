package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Coordenada;
import isi.deso.tp_spring.domain.Vendedor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    Vendedor findFirstByCoordenada(Coordenada coordenada);

    Optional<Vendedor> findById(Integer id);

    List<Vendedor> findByNombreStartingWithIgnoreCase(String nombre);

}
