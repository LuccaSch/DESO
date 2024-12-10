package isi.deso.tp_spring.controller;

import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.service.PedidoService;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
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
        model.addAttribute("pedido", pedido);
        return "pedido";
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
}
