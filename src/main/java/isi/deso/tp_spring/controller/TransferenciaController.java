package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.TransferenciaDTO;
import isi.deso.tp_spring.service.TransferenciaService;
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
@RequestMapping(value = "/api/transferencias", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(final TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public ResponseEntity<List<TransferenciaDTO>> getAllTransferencias() {
        return ResponseEntity.ok(transferenciaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferenciaDTO> getTransferencia(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(transferenciaService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createTransferencia(
            @RequestBody @Valid final TransferenciaDTO transferenciaDTO) {
        final Integer createdId = transferenciaService.create(transferenciaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateTransferencia(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final TransferenciaDTO transferenciaDTO) {
        transferenciaService.update(id, transferenciaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTransferencia(@PathVariable(name = "id") final Integer id) {
        transferenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
