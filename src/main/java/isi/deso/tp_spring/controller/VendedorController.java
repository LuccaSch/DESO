package isi.deso.tp_spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.VendedorService;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(final VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public String getAllVendedores(Model model) {
        List<VendedorDTO> vendedores = vendedorService.findAll();
        model.addAttribute("vendedores", vendedores);
        return "vendedores";
    }

    @GetMapping("/{id}")
    public String getVendedor(@PathVariable(name = "id") final Integer id, Model model) {
        VendedorDTO vendedor = vendedorService.get(id);
        if (vendedor != null) {
            model.addAttribute("vendedor", vendedor);
            return "vendedor";
        } else {
            return "recurso-no-encontrado";
        }
    }

    @GetMapping("/nombre/{nombre}")
    public String getVendedor(@PathVariable(name = "nombre") final String nombre, Model model) {
        VendedorDTO vendedor = vendedorService.getByNombre(nombre);
        if (vendedor != null) {
            model.addAttribute("vendedor", vendedor);
            return "vendedor";
        } else {
            return "recurso-no-encontrado";
        }
    }

    @PostMapping
    public String createVendedor(@ModelAttribute @Valid final VendedorDTO vendedorDTO, Model model) {
        Integer createdId = vendedorService.create(vendedorDTO);
        model.addAttribute("createdId", createdId);
        return "vendedorCreado";
    }

    @PutMapping("/{id}")
    public String updateVendedor(@PathVariable(name = "id") final Integer id,
            @ModelAttribute @Valid final VendedorDTO vendedorDTO, Model model) {
        vendedorService.update(id, vendedorDTO);
        model.addAttribute("id", id);
        return "vendedorActualizado";
    }

    @DeleteMapping("/{id}")
    public String deleteVendedor(@PathVariable(name = "id") final Integer id, Model model) {
        final ReferencedWarning referencedWarning = vendedorService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        vendedorService.delete(id);
        return "vendedorEliminado";
    }

    
}
