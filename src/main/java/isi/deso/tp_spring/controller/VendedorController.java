package isi.deso.tp_spring.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import isi.deso.tp_spring.model.ClienteDTO;
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
    public ResponseEntity<VendedorDTO> getVendedor(@RequestParam final Integer id) {
        VendedorDTO vendedorDTO = vendedorService.get(id);
        return ResponseEntity.ok(vendedorDTO);
    }

    @GetMapping("/api/vendedores/nombre")
    public ResponseEntity<List<VendedorDTO>> getVendedorByNombre(@RequestParam final String nombre) {
        List<VendedorDTO> vendedoresDTO = vendedorService.getByNombre(nombre);
        return ResponseEntity.ok(vendedoresDTO);
    }

    @PostMapping("/api/vendedores")
    @ApiResponse(responseCode = "201")
    @ResponseBody
    public ResponseEntity<Integer> createVendedor(@RequestBody @Valid VendedorDTO vendedorDTO) {
        Integer createdId = vendedorService.create(vendedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    }

    @PutMapping("/api/vendedores/{id}")
    @ApiResponse(responseCode = "200")
    @ResponseBody
    public ResponseEntity<Integer> updateVendedor(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final VendedorDTO vendedorDTO) {
        vendedorService.update(id, vendedorDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/vendedores/{id}")
    @ApiResponse(responseCode = "204")
    @ResponseBody
    public ResponseEntity<Void> deleteVendedor(@PathVariable(name = "id") final Integer id) {
        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/vendedores/menu")
    public String getMenu(@RequestParam Integer vendedorId, Model model) {
        VendedorDTO vendedor = vendedorService.get(vendedorId);
        List<ItemMenuDTO> menuItems = itemMenuService.findByIdVendedor(vendedorId);
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("menuItems", menuItems);
        return "";
    }

}
