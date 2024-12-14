package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.PlatoDTO;
import isi.deso.tp_spring.service.PlatoService;
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
@RequestMapping(value = "/api/platos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlatoController {

    private final PlatoService platoService;

    public PlatoController(final PlatoService platoService) {
        this.platoService = platoService;
    }

    @GetMapping
    public ResponseEntity<List<PlatoDTO>> getAllPlatos() {
        return ResponseEntity.ok(platoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoDTO> getPlato(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(platoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createPlato(@RequestBody @Valid final PlatoDTO platoDTO) {
        final Integer createdId = platoService.create(platoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updatePlato(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final PlatoDTO platoDTO) {
        platoService.update(id, platoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePlato(@PathVariable(name = "id") final Integer id) {
        platoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
