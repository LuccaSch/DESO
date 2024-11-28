package isi.deso.tp_spring.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.VendedorService;
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
@RequestMapping(value = "/api/vendedors", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendedorResource {

    private final VendedorService vendedorService;

    public VendedorResource(final VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> getAllVendedors() {
        return ResponseEntity.ok(vendedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> getVendedor(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(vendedorService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createVendedor(
            @RequestBody @Valid final VendedorDTO vendedorDTO) {
        final Integer createdId = vendedorService.create(vendedorDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateVendedor(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final VendedorDTO vendedorDTO) {
        vendedorService.update(id, vendedorDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVendedor(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = vendedorService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
