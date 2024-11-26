package isi.deso.tp_spring.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.CoordenadaDTO;
import isi.deso.tp_spring.service.CoordenadaService;
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
@RequestMapping(value = "/api/coordenadas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoordenadaResource {

    private final CoordenadaService coordenadaService;

    public CoordenadaResource(final CoordenadaService coordenadaService) {
        this.coordenadaService = coordenadaService;
    }

    @GetMapping
    public ResponseEntity<List<CoordenadaDTO>> getAllCoordenadas() {
        return ResponseEntity.ok(coordenadaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadaDTO> getCoordenada(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(coordenadaService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createCoordenada(
            @RequestBody @Valid final CoordenadaDTO coordenadaDTO) {
        final Integer createdId = coordenadaService.create(coordenadaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateCoordenada(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final CoordenadaDTO coordenadaDTO) {
        coordenadaService.update(id, coordenadaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCoordenada(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = coordenadaService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        coordenadaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
