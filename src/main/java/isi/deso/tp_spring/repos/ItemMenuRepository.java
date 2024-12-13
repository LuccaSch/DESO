package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Categoria;
import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.Vendedor;
import isi.deso.tp_spring.model.ItemMenuDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuRepository extends JpaRepository<ItemMenu, Integer> {

    ItemMenu findFirstByCategoria(Categoria categoria);

    ItemMenu findFirstByVendedor(Vendedor vendedor);

    List<ItemMenuDTO> findByVendedor(Vendedor idVendedor);
}
