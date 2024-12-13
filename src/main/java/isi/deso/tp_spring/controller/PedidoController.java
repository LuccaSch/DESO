package isi.deso.tp_spring.controller;

import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.PedidoService;
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

import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.model.EstadoPedido;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.service.PedidoService;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;

@Controller
public class PedidoController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService pedidoService;

    public PedidoController(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
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
    public String createPedido(@ModelAttribute @Valid final PedidoDTO pedidoDTO, Model model) {
        Integer createdId = pedidoService.create(pedidoDTO);
        model.addAttribute("createdId", createdId);
        return "pedidoCreado";
    }

    @PutMapping("/api/pedido")
    public String updatePedido(@RequestParam final Integer id,
            @ModelAttribute @Valid final PedidoDTO pedidoDTO, Model model) {
        pedidoService.update(id, pedidoDTO);
        model.addAttribute("id", id);
        return "pedidoActualizado";
    }

    @DeleteMapping("/api/pedido")
    public String deletePedido(@RequestParam final Integer id, Model model) {
        final ReferencedWarning referencedWarning = pedidoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        pedidoService.delete(id);
        return "pedidoEliminado";
    }

    @GetMapping("/api/pedidos/detalle")
    public String showAllsItemsPedido(@RequestParam Integer id, Model model) {
        List<ItemPedido> itemsPedido = pedidoService.getItemsPedido(id);
        model.addAttribute("listaItemsPedido", itemsPedido);
        return "lista items pedido";
    }

    @GetMapping("/api/pedidos/estado")
    public String showEstadoPedido(@RequestParam Integer id, Model model) {
        try {
            EstadoPedido estadoPedido = pedidoService.getEstadoPedido(id);
            logger.info("Pedidos encontrados");
            model.addAttribute("listaItemsPedido", estadoPedido);
            return "lista items pedido";
        } catch (NotFoundException ex) {
            logger.info("Pedidos encontrados");
            return "recurso-no-encontrado";
        }
    }

}
