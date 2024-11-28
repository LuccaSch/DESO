package isi.deso.tp_spring.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ItemPedidoDTO;
import isi.deso.tp_spring.service.ItemPedidoService;
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
@RequestMapping(value = "/api/itemPedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemPedidoResource {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoResource(final ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> getAllItemPedidos() {
        return ResponseEntity.ok(itemPedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> getItemPedido(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(itemPedidoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createItemPedido(
            @RequestBody @Valid final ItemPedidoDTO itemPedidoDTO) {
        final Integer createdId = itemPedidoService.create(itemPedidoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateItemPedido(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ItemPedidoDTO itemPedidoDTO) {
        itemPedidoService.update(id, itemPedidoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteItemPedido(@PathVariable(name = "id") final Integer id) {
        itemPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
