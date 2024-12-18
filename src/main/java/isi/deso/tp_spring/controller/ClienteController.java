package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.service.ClienteService;
import isi.deso.tp_spring.util.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @GetMapping("/api/clientes/id")
    public ResponseEntity<ClienteDTO> getCliente(@RequestParam @NotNull final Integer id) {
        ClienteDTO cliente = clienteService.get(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/api/clientes/nombre")
    public ResponseEntity<List<ClienteDTO>> getClienteByNombre(@RequestParam @NotBlank final String nombre) {
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

    @PutMapping("/api/clientes/{id}")
    @ApiResponse(responseCode = "200")
    @ResponseBody
    public ResponseEntity<Integer> updateCliente(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ClienteDTO clienteDTO) {
        clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ApiResponse(responseCode = "204")
    @ResponseBody
    public ResponseEntity<Void> deleteCliente(@PathVariable(name = "id") final Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

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
