package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.MercadoPagoDTO;
import isi.deso.tp_spring.service.MercadoPagoService;
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
@RequestMapping(value = "/api/mercadoPagos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MercadoPagoController {

    private final MercadoPagoService mercadoPagoService;

    public MercadoPagoController(final MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @GetMapping
    public ResponseEntity<List<MercadoPagoDTO>> getAllMercadoPagos() {
        return ResponseEntity.ok(mercadoPagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MercadoPagoDTO> getMercadoPago(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(mercadoPagoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createMercadoPago(
            @RequestBody @Valid final MercadoPagoDTO mercadoPagoDTO) {
        final Integer createdId = mercadoPagoService.create(mercadoPagoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateMercadoPago(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final MercadoPagoDTO mercadoPagoDTO) {
        mercadoPagoService.update(id, mercadoPagoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMercadoPago(@PathVariable(name = "id") final Integer id) {
        mercadoPagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
