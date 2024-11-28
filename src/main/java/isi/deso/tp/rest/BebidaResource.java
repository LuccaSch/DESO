package isi.deso.tp_spring.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.BebidaDTO;
import isi.deso.tp_spring.service.BebidaService;
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
@RequestMapping(value = "/api/bebidas", produces = MediaType.APPLICATION_JSON_VALUE)
public class BebidaResource {

    private final BebidaService bebidaService;

    public BebidaResource(final BebidaService bebidaService) {
        this.bebidaService = bebidaService;
    }

    @GetMapping
    public ResponseEntity<List<BebidaDTO>> getAllBebidas() {
        return ResponseEntity.ok(bebidaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebidaDTO> getBebida(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(bebidaService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createBebida(@RequestBody @Valid final BebidaDTO bebidaDTO) {
        final Integer createdId = bebidaService.create(bebidaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateBebida(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final BebidaDTO bebidaDTO) {
        bebidaService.update(id, bebidaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBebida(@PathVariable(name = "id") final Integer id) {
        bebidaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
