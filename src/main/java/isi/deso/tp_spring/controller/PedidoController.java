package isi.deso.tp_spring.controller;

import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.ItemMenuService;
import isi.deso.tp_spring.service.PedidoService;
import isi.deso.tp_spring.service.VendedorService;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.domain.ItemMenu;
import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.model.EstadoPedido;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.model.ItemPedidoDTO;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.service.PedidoService;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;

@Controller
public class PedidoController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService pedidoService;
    private final ItemMenuService itemMenuService;
    private final VendedorService vendedorService;

    public PedidoController(final PedidoService pedidoService, final ItemMenuService itemMenuService,
            final VendedorService vendedorService) {
        this.pedidoService = pedidoService;
        this.itemMenuService = itemMenuService;
        this.vendedorService = vendedorService;
    }

    @GetMapping("/api/pedidos")
    public String getAllPedidos(Model model) {
        List<PedidoDTO> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedidos";
    }

    @GetMapping("/api/pedido")
    public String getPedido(@RequestParam final Integer id, Model model) {
        try {
            PedidoDTO pedido = pedidoService.get(id);
            logger.info("Pedido encontrado");
            model.addAttribute("pedido", pedido);
            return "pedido";
        } catch (NotFoundException ex) {
            logger.info("Pedido no encontrado");
            return "recurso-no-encontrado";
        }
    }

    @PostMapping("/api/pedido")
    @ApiResponse(responseCode = "201")
    public String createPedido(@ModelAttribute @Valid final PedidoDTO pedidoDTO, Model model,
            RedirectAttributes redirectAttributes) {
        Integer createdId = pedidoService.create(pedidoDTO);
        model.addAttribute("createdId", createdId);
        redirectAttributes.addFlashAttribute("successMessage", "¡Creado con éxito!");
        return "redirect:/api/pedidos";
    }

    @PutMapping("/api/pedido")
    @ApiResponse(responseCode = "200")
    public String updatePedido(@RequestParam final Integer id,
            @ModelAttribute @Valid final PedidoDTO pedidoDTO, Model model, RedirectAttributes redirectAttributes) {
        pedidoService.update(id, pedidoDTO);
        model.addAttribute("id", id);
        redirectAttributes.addFlashAttribute("successMessage", "¡Actualizado con éxito!");
        return "redirect:/api/pedidos";
    }

    @GetMapping("/api/pedido/eliminar")
    @ApiResponse(responseCode = "204")
    public String deletePedido(@RequestParam final Integer id, Model model, RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = pedidoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        pedidoService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "¡Eliminado con éxito!");
        return "redirect:/api/pedidos";
    }

    @GetMapping("/api/pedido/detalle")
    public String showAllsItemsPedido(@RequestParam Integer id, Model model) {
        List<ItemPedido> itemsPedido = pedidoService.getItemsPedido(id);
        model.addAttribute("lista-items-pedido", itemsPedido);
        return "lista-items-pedido";
    }

    @GetMapping("/api/pedido/estado")
    public String showEstadoPedido(@RequestParam Integer id, Model model) {
        try {
            EstadoPedido estadoPedido = pedidoService.getEstadoPedido(id);
            logger.info("Pedidos encontrados");
            model.addAttribute("lista-items-pedido", estadoPedido);
            return "lista-items-pedido";
        } catch (NotFoundException ex) {
            logger.info("Pedidos encontrados");
            return "recurso-no-encontrado";
        }
    }

    @PutMapping("/api/pedido/editar")
    public String updatePedidoPorVista(@RequestParam final Integer id, Model model,
            RedirectAttributes redirectAttributes) {
        PedidoDTO pedido = pedidoService.get(id);
        if (pedido == null) {
            return "recurso-no-encontrado";
        }

        EstadoPedido estadoPedido = pedidoService.getEstadoPedido(id);
        if (estadoPedido == EstadoPedido.CANCELADO || estadoPedido == EstadoPedido.ENTREGADO
                || estadoPedido == EstadoPedido.ENVIADO) {
            return "no-se-puede-editar-pedido";
        } else if (estadoPedido == EstadoPedido.RECIBIDO || estadoPedido == EstadoPedido.PREPARANDO) {
            List<ItemPedidoDTO> itemPedidosDTO = pedidoService.getItemsPedidoDTO(id);

            VendedorDTO vendedorDTO = null;
            if (!itemPedidosDTO.isEmpty()) {
                Integer idItemMenu = itemPedidosDTO.get(0).getItemMenu();
                ItemMenuDTO itemMenuDTO = itemMenuService.get(idItemMenu);
                vendedorDTO = vendedorService.get(itemMenuDTO.getVendedor());
            }

            List<ItemMenuDTO> menuItems = null;
            if (vendedorDTO != null) {
                menuItems = itemMenuService.findByIdVendedor(vendedorDTO.getId());
            }

            model.addAttribute("pedido", pedido);
            model.addAttribute("menuItems", menuItems);

            redirectAttributes.addFlashAttribute("successMessage", "¡Actualizado con éxito!");

            return "editar-pedido";
        }

        return "recurso-no-encontrado";

    }

}
