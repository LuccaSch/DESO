package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ItemMenuDTO;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.ItemMenuService;
import isi.deso.tp_spring.service.VendedorService;
import isi.deso.tp_spring.util.NotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VendedorController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(VendedorController.class);

    private final VendedorService vendedorService;
    private final ItemMenuService itemMenuService;

    public VendedorController(final VendedorService vendedorService, final ItemMenuService itemMenuService) {
        this.vendedorService = vendedorService;
        this.itemMenuService = itemMenuService;
    }

    @GetMapping("/api/vendedores")
    public String getAllVendedores(Model model) {
        List<VendedorDTO> vendedores = vendedorService.findAll();
        model.addAttribute("vendedores", vendedores);
        return "vendedores";
    }

    @GetMapping("/api/vendedores/id")
    public String getVendedor(@RequestParam final Integer id, Model model) {
        try {
            VendedorDTO vendedor = vendedorService.get(id);
            logger.info("Vendedor encontrado");
            model.addAttribute("vendedor", vendedor);
            return "vendedor";
        } catch (NotFoundException ex) {
            logger.info("Vendedor no encontrado");
            return "recurso-no-encontrado";
        }
    }

    @GetMapping("/api/vendedores/nombre")
    public String getVendedorByNombre(@RequestParam final String nombre, Model model) {
        try {
            VendedorDTO vendedor = vendedorService.getByNombre(nombre);
            logger.info("Vendedor encontrado");
            model.addAttribute("vendedor", vendedor);
            return "vendedor";
        } catch (NotFoundException ex) {
            logger.info("Vendedor no encontrado");
            return "recurso-no-encontrado";
        }
    }

    @PostMapping("/api/vendedores")
    @ApiResponse(responseCode = "201")
    @ResponseBody
    public ResponseEntity<Integer> createVendedor(@RequestBody @Valid VendedorDTO vendedorDTO) {
        Integer createdId = vendedorService.create(vendedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    }

    // @PostMapping("/api/vendedor/guardar")
    // @ApiResponse(responseCode = "201")
    // public String createVendedor(@ModelAttribute @Valid final VendedorDTO
    // vendedorDTO, Model model,
    // RedirectAttributes redirectAttributes) {
    // Integer createdId = vendedorService.create(vendedorDTO);
    // model.addAttribute("createdId", createdId);
    // redirectAttributes.addFlashAttribute("successMessage", "¡Creado con éxito!");
    // return "redirect:/api/vendedores";
    // }
    @PutMapping("/api/vendedores/{id}")
    @ApiResponse(responseCode = "200")
    @ResponseBody
    public ResponseEntity<Integer> updateVendedor(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final VendedorDTO vendedorDTO) {
        vendedorService.update(id, vendedorDTO);
        return ResponseEntity.ok(id);
    }

    // @PutMapping("/api/vendedores/editar")
    // @ApiResponse(responseCode = "200")
    // public String updateVendedor(@RequestParam final Integer id,
    // @ModelAttribute @Valid final VendedorDTO vendedorDTO, Model model,
    // RedirectAttributes redirectAttributes) {
    // vendedorService.update(id, vendedorDTO);
    // model.addAttribute("id", id);
    // redirectAttributes.addFlashAttribute("successMessage", "¡Actualizado con
    // éxito!");
    // return "redirect:/api/vendedores";
    // }
    @DeleteMapping("/api/vendedores/{id}")
    @ApiResponse(responseCode = "204")
    @ResponseBody
    public ResponseEntity<Void> deleteVendedor(@PathVariable(name = "id") final Integer id) {
        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/api/vendedores/eliminar/{id}")
    // @ApiResponse(responseCode = "204")
    // public String deleteVendedor(@PathVariable(name = "id") final Integer id,
    // Model model,
    // RedirectAttributes redirectAttributes) {
    // final ReferencedWarning referencedWarning =
    // vendedorService.getReferencedWarning(id);
    // if (referencedWarning != null) {
    // throw new ReferencedException(referencedWarning);
    // }
    // vendedorService.delete(id);
    // redirectAttributes.addFlashAttribute("successMessage", "¡Eliminado con
    // éxito!");
    // return "redirect:/api/vendedores";
    // }
    @GetMapping("/api/vendedores/menu")
    public String getMenu(@RequestParam Integer vendedorId, Model model) {
        VendedorDTO vendedor = vendedorService.get(vendedorId);
        List<ItemMenuDTO> menuItems = itemMenuService.findByIdVendedor(vendedorId);
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("menuItems", menuItems);
        return "";
    }

}
