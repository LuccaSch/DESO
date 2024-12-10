package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.EfectivoDTO;
import isi.deso.tp_spring.service.EfectivoService;
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
@RequestMapping(value = "/api/efectivos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EfectivoController {

    private final EfectivoService efectivoService;

    public EfectivoController(final EfectivoService efectivoService) {
        this.efectivoService = efectivoService;
    }

    @GetMapping
    public ResponseEntity<List<EfectivoDTO>> getAllEfectivos() {
        return ResponseEntity.ok(efectivoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EfectivoDTO> getEfectivo(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(efectivoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createEfectivo(
            @RequestBody @Valid final EfectivoDTO efectivoDTO) {
        final Integer createdId = efectivoService.create(efectivoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateEfectivo(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final EfectivoDTO efectivoDTO) {
        efectivoService.update(id, efectivoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEfectivo(@PathVariable(name = "id") final Integer id) {
        efectivoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
