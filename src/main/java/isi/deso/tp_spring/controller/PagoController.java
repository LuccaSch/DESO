package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.PagoDTO;
import isi.deso.tp_spring.service.PagoService;
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
@RequestMapping(value = "/api/pagos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PagoController {

    private final PagoService pagoService;

    public PagoController(final PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> getAllPagos() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getPago(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(pagoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createPago(@RequestBody @Valid final PagoDTO pagoDTO) {
        final Integer createdId = pagoService.create(pagoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updatePago(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final PagoDTO pagoDTO) {
        pagoService.update(id, pagoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePago(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = pagoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
