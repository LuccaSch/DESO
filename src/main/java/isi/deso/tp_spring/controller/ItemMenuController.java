package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.model.PedidoDTO;
import isi.deso.tp_spring.service.ItemMenuService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemMenuController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ItemMenuController.class);

    private final ItemMenuService itemMenuService;

    public ItemMenuController(final ItemMenuService itemMenuService) {
        this.itemMenuService = itemMenuService;
    }

    @GetMapping("/api/itemsMenu")
    public String getAllItemMenus(Model model) {
        List<ItemMenuDTO> itemsMenuDTO = itemMenuService.findAll();
        model.addAttribute("itemsMenu", itemsMenuDTO);
        return "items-menu";
    }

    @GetMapping("/api/itemsMenu/id")
    public ResponseEntity<ItemMenuDTO> getItemMenu(@RequestParam final Integer id) {
        ItemMenuDTO itemMenuDTO = itemMenuService.get(id);
        return ResponseEntity.ok(itemMenuDTO);
    }

    @GetMapping("/api/itemsMenu/precio")
    public ResponseEntity<List<ItemMenuDTO>> getItemMenuByPrecio(@RequestParam final Double precio) {
        List<ItemMenuDTO> itemMenusDTO = itemMenuService.getByPrecio(precio);
        return ResponseEntity.ok(itemMenusDTO);
    }

    @GetMapping("/api/itemsMenu/editar/{id}")
    public String showEditForm(@PathVariable(name = "id") final Integer id, Model model) {
        ItemMenuDTO itemMenu = itemMenuService.get(id);
        model.addAttribute("itemMenu", itemMenu);
        return "itemMenuEdit";
    }

    @PutMapping("/api/itemsMenu/{id}")
    @ApiResponse(responseCode = "200")
    @ResponseBody
    public ResponseEntity<Integer> updateItemMenu(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ItemMenuDTO itemMenuDTO) {
        itemMenuService.update(id, itemMenuDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/itemsMenu/{id}")
    @ApiResponse(responseCode = "204")
    @ResponseBody
    public ResponseEntity<Void> deleteItemMenu(@PathVariable(name = "id") final Integer id) {
        itemMenuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
