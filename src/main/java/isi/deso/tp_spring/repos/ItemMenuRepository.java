package isi.deso.tp_spring.repos;

import isi.deso.tp_spring.domain.Categoria;
import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMenuRepository extends JpaRepository<ItemMenu, Integer> {

    ItemMenu findFirstByCategoria(Categoria categoria);

    ItemMenu findFirstByVendedor(Vendedor vendedor);

}
