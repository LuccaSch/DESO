package isi.deso.tp_spring.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.service.PedidoService;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoResource {

    private final PedidoService pedidoService;

    public PedidoResource(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(pedidoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createPedido(@RequestBody @Valid final PedidoDTO pedidoDTO) {
        final Integer createdId = pedidoService.create(pedidoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updatePedido(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final PedidoDTO pedidoDTO) {
        pedidoService.update(id, pedidoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePedido(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = pedidoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
