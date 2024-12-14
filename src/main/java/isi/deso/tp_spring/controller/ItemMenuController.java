package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ItemMenuDTO;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/itemMenus")
public class ItemMenuController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ItemMenuController.class);

    private final ItemMenuService itemMenuService;

    public ItemMenuController(final ItemMenuService itemMenuService) {
        this.itemMenuService = itemMenuService;
    }

    @GetMapping
    public String getAllItemMenus(Model model) {
        List<ItemMenuDTO> itemMenus = itemMenuService.findAll();
        model.addAttribute("itemMenus", itemMenus);
        return "itemMenuList";
    }

    @GetMapping("/{id}")
    public String getItemMenu(@PathVariable(name = "id") final Integer id, Model model) {
        ItemMenuDTO itemMenu = itemMenuService.get(id);
        if (itemMenu != null) {
            logger.info("Item menú encontrado");
            model.addAttribute("itemMenuDetail", itemMenu);
            return "itemMenu";
        } else {
            logger.info("Item menú no encontrado");
            return "recurso-no-encontrado";
        }

    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable(name = "id") final Integer id, Model model) {
        ItemMenuDTO itemMenu = itemMenuService.get(id);
        model.addAttribute("itemMenu", itemMenu);
        return "itemMenuEdit";
    }

    // @PostMapping("/api/itemMenus")
    // @ApiResponse(responseCode = "201")
    // @ResponseBody
    // public ResponseEntity<Integer> createItemMenu(@RequestBody @Valid ItemMenuDTO
    // itemMenuDTO) {
    // Integer createdId = itemMenuService.create(itemMenuDTO);
    // return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    // }
    @PutMapping("/api/itemMenus/{id}")
    @ApiResponse(responseCode = "200")
    @ResponseBody
    public ResponseEntity<Integer> updateItemMenu(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ItemMenuDTO itemMenuDTO) {
        itemMenuService.update(id, itemMenuDTO);
        return ResponseEntity.ok(id);
    }

    // @PutMapping("/editar/{id}")
    // public String updateItemMenu(@PathVariable(name = "id") final Integer id,
    // @ModelAttribute @Valid final ItemMenuDTO itemMenuDTO, Model model) {
    // itemMenuService.update(id, itemMenuDTO);
    // return "redirect:/ItemMenus";
    // }
    @DeleteMapping("/api/itemMenus/{id}")
    @ApiResponse(responseCode = "204")
    @ResponseBody
    public ResponseEntity<Void> deleteItemMenu(@PathVariable(name = "id") final Integer id) {
        itemMenuService.delete(id);
        return ResponseEntity.noContent().build();
    }
    //
    // @DeleteMapping("/eliminar/{id}")
    // public String deleteItemMenu(@PathVariable(name = "id") final Integer id) {
    // itemMenuService.delete(id);
    // return "redirect:/ItemMenus";
    // }
}
