package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.domain.ItemPedido;
import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.model.EstadoPedido;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.model.ItemPedidoDTO;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.ItemMenuService;
import isi.deso.tp_spring.service.PedidoService;
import isi.deso.tp_spring.service.VendedorService;
import isi.deso.tp_spring.util.NotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/api/pedidos/id")
    public ResponseEntity<PedidoDTO> getPedido(@RequestParam final Integer id) {
        PedidoDTO pedidoDTO = pedidoService.get(id);
        return ResponseEntity.ok(pedidoDTO);
    }

    @GetMapping("/api/pedidos/estadoPedido")
    public ResponseEntity<List<PedidoDTO>> getPedidoByEstadoPedido(@RequestParam final Integer estadoPedido) {
        List<PedidoDTO> pedidosDTO = pedidoService.getByEstadoPedido(estadoPedido);
        return ResponseEntity.ok(pedidosDTO);
    }

    @PostMapping("/api/pedidos")
    @ApiResponse(responseCode = "201")
    @ResponseBody
    public ResponseEntity<Integer> createPedido(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Integer createdId = pedidoService.create(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    }

    @PutMapping("/api/pedidos/{id}")
    @ApiResponse(responseCode = "200")
    @ResponseBody
    public ResponseEntity<Integer> updatePedido(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final PedidoDTO pedidoDTO) {
        pedidoService.update(id, pedidoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/pedidos/{id}")
    @ApiResponse(responseCode = "204")
    @ResponseBody
    public ResponseEntity<Void> deletePedido(@PathVariable(name = "id") final Integer id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/pedidos/detalle")
    public String showAllsItemsPedido(@RequestParam Integer id, Model model) {
        List<ItemPedido> itemsPedido = pedidoService.getItemsPedido(id);
        model.addAttribute("lista-items-pedido", itemsPedido);
        return "lista-items-pedido";
    }

    @GetMapping("/api/pedidos/estado")
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

    @PutMapping("/api/pedidos/editar")
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
