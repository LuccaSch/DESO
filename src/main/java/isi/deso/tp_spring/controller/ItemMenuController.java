package isi.deso.tp_spring.controller;

import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.service.ItemMenuService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ItemMenus")
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

    @PutMapping("/editar/{id}")
    public String updateItemMenu(@PathVariable(name = "id") final Integer id,
            @ModelAttribute @Valid final ItemMenuDTO itemMenuDTO, Model model) {
        itemMenuService.update(id, itemMenuDTO);
        return "redirect:/ItemMenus";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteItemMenu(@PathVariable(name = "id") final Integer id) {
        itemMenuService.delete(id);
        return "redirect:/ItemMenus";
    }
}
