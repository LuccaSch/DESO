package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.service.ClienteService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // @GetMapping("/api/clientes/id")
    // public String getCliente(@RequestParam final Integer id, Model model) {
    // try {
    // List<ClienteDTO> clientes = clienteService.get(id);
    // logger.info("Clientes encontrado");
    // model.addAttribute("clientes", clientes);
    // return "clientes";
    // } catch (NotFoundException ex) {
    // logger.info("Cliente no encontrado");
    // return "recurso-no-encontrado";
    // }
    // }

    // @GetMapping("/api/clientes/nombre")
    // public String getClienteByNombre(@RequestParam final String nombre, Model
    // model) {
    // try {
    // List<ClienteDTO> clientes = clienteService.getByNombre(nombre);
    // logger.info("Clientes encontrado");
    // model.addAttribute("clientes", clientes);
    // return "clientes";
    // } catch (NotFoundException ex) {
    // logger.info("Clientes no encontrado");
    // return "recurso-no-encontrado";
    // }
    // }

    @GetMapping("/api/clientes/id")
    public ResponseEntity<ClienteDTO> getCliente(@RequestParam final Integer id) {
        ClienteDTO cliente = clienteService.get(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/api/clientes/nombre")
    public ResponseEntity<List<ClienteDTO>> getClienteByNombre(@RequestParam final String nombre) {
        List<ClienteDTO> clientes = clienteService.getByNombre(nombre);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/api/clientes")
    @ApiResponse(responseCode = "201")
    @ResponseBody
    public ResponseEntity<Integer> createCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        Integer createdId = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    }

    // @PostMapping("/api/cliente/crear")
    // @ApiResponse(responseCode = "201")
    // public String createCliente(@ModelAttribute @Valid ClienteDTO clienteDTO,
    // Model model,
    // RedirectAttributes redirectAttributes) {
    // Integer createdId = clienteService.create(clienteDTO);
    // model.addAttribute("createdId", createdId);
    // redirectAttributes.addFlashAttribute("successMessage", "¡Creado con éxito!");
    // return "redirect:/api/clientes";
    // }
    @PutMapping("/api/clientes/{id}")
    @ApiResponse(responseCode = "200")
    @ResponseBody
    public ResponseEntity<Integer> updateCliente(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ClienteDTO clienteDTO) {
        clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(id);
    }

    // @PutMapping("/api/cliente")
    // @ApiResponse(responseCode = "200")
    // public String updateCliente(@RequestParam final Integer id, @ModelAttribute
    // @Valid ClienteDTO clienteDTO,
    // Model model, RedirectAttributes redirectAttributes) {
    // clienteService.update(id, clienteDTO);
    // model.addAttribute("id", id);
    // redirectAttributes.addFlashAttribute("successMessage", "¡Actualizado con
    // éxito!");
    // return "redirect:/api/clientes";
    // }
    @DeleteMapping("/api/clientes/{id}")
    @ApiResponse(responseCode = "204")
    @ResponseBody
    public ResponseEntity<Void> deleteCliente(@PathVariable(name = "id") final Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/api/cliente/eliminar/{id}")
    // @ApiResponse(responseCode = "204")
    // public String deleteCliente(@PathVariable(name = "id") final Integer id,
    // Model model,
    // RedirectAttributes redirectAttributes) {
    // final ReferencedWarning referencedWarning =
    // clienteService.getReferencedWarning(id);
    // if (referencedWarning != null) {
    // throw new ReferencedException(referencedWarning);
    // }
    // clienteService.delete(id);
    // redirectAttributes.addFlashAttribute("successMessage", "¡Eliminado con
    // éxito!");
    // return "redirect:/api/clientes";
    // }
    @GetMapping("/api/clientes/editar")
    public String showUpdateForm(@RequestParam final Integer id, Model model) {
        ClienteDTO cliente = clienteService.get(id);
        model.addAttribute("cliente", cliente);
        return "editar-cliente";
    }

    @PostMapping("/api/clientes/guardar")
    public String postMethodName(@ModelAttribute ClienteDTO cliente) {
        clienteService.create(cliente);
        return "redirect:/api/clientes";
    }

    @GetMapping("/api/clientes/pedidos/detalle")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
