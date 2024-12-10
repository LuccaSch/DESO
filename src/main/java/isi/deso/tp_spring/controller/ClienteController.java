package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.service.ClienteService;
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

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String getAllClientes(Model model) {
        List<ClienteDTO> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/{id}")
    public String getCliente(@PathVariable final Integer id, Model model) {
        logger.info("getCliente");
        ClienteDTO cliente = clienteService.get(id);
        if (cliente != null) {
            logger.info("Cliente encontrado");
            model.addAttribute("cliente", cliente);
            return "cliente";
        } else {
            logger.info("Cliente no encontrado");
            return "recurso-no-encontrado";
        }
    }

    @GetMapping("/nombre/{nombre}")
    public String getClienteByNombre(@PathVariable final String nombre, Model model) {
        ClienteDTO cliente = clienteService.getByNombre(nombre);
        if (cliente != null) {
            logger.info("Cliente encontrado");
            model.addAttribute("cliente", cliente);
            return "cliente";
        } else {
            logger.info("Cliente no encontrado");
            return "recurso-no-encontrado";
        }
    }

    @GetMapping("/crear")
    public String showCreateForm(Model model) {
        model.addAttribute("cliente", new ClienteDTO()); // Para mostrar un formulario vacío
        return "crearCliente";
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public String createCliente(@ModelAttribute @Valid ClienteDTO clienteDTO, Model model) {
        Integer createdId = clienteService.create(clienteDTO);
        model.addAttribute("createdId", createdId);
        return "clienteCreado";
    }

    @GetMapping("/editar/{id}")
    public String showUpdateForm(@PathVariable(name = "id") final Integer id, Model model) {
        ClienteDTO cliente = clienteService.get(id);
        model.addAttribute("cliente", cliente);
        return "editarCliente";
    }

    @PutMapping("/{id}")
    public String updateCliente(@PathVariable(name = "id") final Integer id,
            @ModelAttribute @Valid ClienteDTO clienteDTO, Model model) {
        clienteService.update(id, clienteDTO);
        model.addAttribute("id", id);
        return "clienteActualizado"; // Nombre de la plantilla Thymeleaf (clienteActualizado.html)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public String deleteCliente(@PathVariable(name = "id") final Integer id, Model model) {
        final ReferencedWarning referencedWarning = clienteService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        clienteService.delete(id);
        return "clienteEliminado";
    }
}
