package isi.deso.tp_spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.model.EstadoPedido;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.model.ItemPedidoDTO;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.ItemMenuService;
import isi.deso.tp_spring.service.PedidoService;
import isi.deso.tp_spring.service.VendedorService;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/api/pedidos")
public class PedidoController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService pedidoService;
    private final ItemMenuService itemMenuService;
    private final VendedorService vendedorService;

    public PedidoController(final PedidoService pedidoService, 
                    final ItemMenuService itemMenuService, 
                    final VendedorService vendedorService) {
        this.pedidoService = pedidoService;
        this.itemMenuService = itemMenuService;
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public String getAllPedidos(Model model) {
        List<PedidoDTO> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedidos";
    }

    @GetMapping("/{id}")
    public String getPedido(@PathVariable(name = "id") final Integer id, Model model) {
        PedidoDTO pedido = pedidoService.get(id);
        if (pedido != null) {
            logger.info("Pedido encontrado");
            model.addAttribute("itemMenuDetail", pedido);
            return "pedido";
        } else {
            logger.info("Pedido no encontrado");
            return "recurso-no-encontrado";
        }
    }

    @PostMapping
    public String createPedido(@ModelAttribute @Valid final PedidoDTO pedidoDTO, Model model) {
        Integer createdId = pedidoService.create(pedidoDTO);
        model.addAttribute("createdId", createdId);
        return "pedidoCreado";
    }

    @PutMapping("/{id}")
    public String updatePedido(@PathVariable(name = "id") final Integer id,
            @ModelAttribute @Valid final PedidoDTO pedidoDTO, Model model) {
        pedidoService.update(id, pedidoDTO);
        model.addAttribute("id", id);
        return "pedidoActualizado";
    }

    @DeleteMapping("/{id}")
    public String deletePedido(@PathVariable(name = "id") final Integer id, Model model) {
        final ReferencedWarning referencedWarning = pedidoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        pedidoService.delete(id);
        return "pedidoEliminado";
    }

    @GetMapping("/{id}/detalle")
    public String showAllsItemsPedido(@PathVariable Integer id, Model model) {
        List<ItemPedido> itemsPedido = pedidoService.getItemsPedido(id);
        model.addAttribute("listaItemsPedido", itemsPedido);
        return "lista items pedido";
    }

    @GetMapping("/{id}/estado")
    public String showEstadoPedido(@PathVariable Integer id, Model model) {
        EstadoPedido estadoPedido = pedidoService.getEstadoPedido(id);
        model.addAttribute("listaItemsPedido", estadoPedido);
        return "lista items pedido";
    }

    @GetMapping("/editarPedido")
    public String getMethodName(@PathVariable("id") final Integer id, Model model, RedirectAttributes redirectAttributes) {
        PedidoDTO pedido = pedidoService.get(id);
        if(pedido == null){
            return "recursno-no-encontrado";
        }

        EstadoPedido estadoPedido = pedidoService.getEstadoPedido(id);
        if(estadoPedido==EstadoPedido.CANCELADO || estadoPedido==EstadoPedido.ENTREGADO || estadoPedido==EstadoPedido.ENVIADO){
            
            List<ItemPedidoDTO> itemPedidosDTO = pedidoService.getItemsPedidoDTO(id);
            
            // Obtener el primer vendedor a partir del primer itemMenu
            // Asumimos que todos los ItemMenus de este pedido son de un solo vendedor
            VendedorDTO vendedorDTO = null;
            if (!itemPedidosDTO.isEmpty()) {
                ItemMenu itemMenu = itemPedidosDTO.get(0).getItemMenu(); // Obtén el primer ItemMenu
                vendedorDTO = vendedorService.mapToDTO(itemMenu.getVendedor(), vendedorDTO); // Obtén el vendedor de ese ItemMenu
            }

            List<ItemMenuDTO> menuItems = null;
            if(vendedorDTO != null){
                menuItems = itemMenuService.getItemsDeVendedor(vendedorDTO.getId());
            }
            // Agregar a la vista
            model.addAttribute("pedido", pedido);
            model.addAttribute("menuItems", menuItems);

            redirectAttributes.addFlashAttribute("successMessage", "¡Actualizado con éxito!"); //mostrar swipe up de actualizado con exito
            return "editarPedido";
        }else{
            return ""; //agregar una plantilla que maneje que no se pueda editar el pedido
        }
    }
    
}
