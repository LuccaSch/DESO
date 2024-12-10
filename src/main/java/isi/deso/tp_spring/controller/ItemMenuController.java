package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.service.ItemMenuService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/ItemMenus", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemMenuController {

    private final ItemMenuService ItemMenuService;

    public ItemMenuController(final ItemMenuService ItemMenuService) {
        this.ItemMenuService = ItemMenuService;
    }

    @GetMapping
    public ResponseEntity<List<ItemMenuDTO>> getAllItemMenus() {
        return ResponseEntity.ok(ItemMenuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMenuDTO> getItemMenu(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(ItemMenuService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateItemMenu(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ItemMenuDTO ItemMenuDTO) {
        ItemMenuService.update(id, ItemMenuDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteItemMenu(@PathVariable(name = "id") final Integer id) {
        ItemMenuService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
