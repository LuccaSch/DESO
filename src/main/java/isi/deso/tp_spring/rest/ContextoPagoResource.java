package isi.deso.tp_spring.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ContextoPagoDTO;
import isi.deso.tp_spring.service.ContextoPagoService;
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
@RequestMapping(value = "/api/contextosPagos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContextoPagoResource {

    private final ContextoPagoService contextoPagoService;

    public ContextoPagoResource(final ContextoPagoService contextoPagoService) {
        this.contextoPagoService = contextoPagoService;
    }

    @GetMapping
    public ResponseEntity<List<ContextoPagoDTO>> getAllContextoPagos() {
        return ResponseEntity.ok(contextoPagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContextoPagoDTO> getContextoPago(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(contextoPagoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createContextoPago(
            @RequestBody @Valid final ContextoPagoDTO contextoPagoDTO) {
        final Integer createdId = contextoPagoService.create(contextoPagoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateContextoPago(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ContextoPagoDTO contextoPagoDTO) {
        contextoPagoService.update(id, contextoPagoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteContextoPago(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = contextoPagoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        contextoPagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
