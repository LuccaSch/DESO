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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.service.ClienteService;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;

@Controller
public class ClienteController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/api/clientes")
    public String getAllClientes(Model model) {
        List<ClienteDTO> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/api/cliente")
    public String getCliente(@RequestParam final Integer id, Model model) {
        try {
            ClienteDTO cliente = clienteService.get(id);
            logger.info("Cliente encontrado");
            model.addAttribute("cliente", cliente);
            return "cliente";
        } catch (NotFoundException ex) {
            logger.info("Cliente no encontrado");
            return "recurso-no-encontrado";
        }
    }

    @GetMapping("/api/cliente/nombre")
    public String getClienteByNombre(@RequestParam final String nombre, Model model) {
        try {
            ClienteDTO cliente = clienteService.getByNombre(nombre);
            logger.info("Cliente encontrado");
            model.addAttribute("cliente", cliente);
            return "cliente";
        } catch (NotFoundException ex) {
            logger.info("Cliente no encontrado");
            return "recurso-no-encontrado";
        }
    }

    // @GetMapping("/api/cliente/crear")
    // public String showCreateForm(Model model) {
    // model.addAttribute("cliente", new ClienteDTO()); // Para mostrar un
    // formulario vacío
    // return "crear-cliente";
    // }

    @PostMapping("/api/cliente/crear")
    @ApiResponse(responseCode = "201")
    public String createCliente(@ModelAttribute @Valid ClienteDTO clienteDTO, Model model,
            RedirectAttributes redirectAttributes) {
        Integer createdId = clienteService.create(clienteDTO);
        model.addAttribute("createdId", createdId);
        redirectAttributes.addFlashAttribute("successMessage", "¡Creado con éxito!");
        return "redirect:/api/clientes";
    }

    @GetMapping("/api/cliente/editar")
    public String showUpdateForm(@RequestParam final Integer id, Model model) {
        ClienteDTO cliente = clienteService.get(id);
        model.addAttribute("cliente", cliente);
        return "editar-cliente";
    }

    @PutMapping("/api/cliente")
    @ApiResponse(responseCode = "200")
    public String updateCliente(@RequestParam final Integer id, @ModelAttribute @Valid ClienteDTO clienteDTO,
            Model model, RedirectAttributes redirectAttributes) {
        clienteService.update(id, clienteDTO);
        model.addAttribute("id", id);
        redirectAttributes.addFlashAttribute("successMessage", "¡Actualizado con éxito!");
        return "redirect:/api/clientes";
    }

    @GetMapping("/api/cliente/eliminar/{id}")
    @ApiResponse(responseCode = "204")
    public String deleteCliente(@PathVariable(name = "id") final Integer id, Model model,
            RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = clienteService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        clienteService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "¡Eliminado con éxito!");
        return "redirect:/api/clientes";
    }

    @PostMapping("/api/cliente/guardar")
    public String postMethodName(@ModelAttribute ClienteDTO cliente) {
        clienteService.create(cliente);
        return "redirect:/api/clientes";
    }

    @GetMapping("/api/cliente/pedidos/detalle")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
