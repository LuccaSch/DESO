package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
