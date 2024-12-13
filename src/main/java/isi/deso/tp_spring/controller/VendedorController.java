package isi.deso.tp_spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import isi.deso.tp_spring.model.ClienteDTO;
import isi.deso.tp_spring.model.VendedorDTO;
import isi.deso.tp_spring.service.VendedorService;
import isi.deso.tp_spring.util.NotFoundException;
import isi.deso.tp_spring.util.ReferencedException;
import isi.deso.tp_spring.util.ReferencedWarning;
import jakarta.validation.Valid;

@Controller
public class VendedorController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(VendedorController.class);

    private final VendedorService vendedorService;

    public VendedorController(final VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping("/api/vendedores")
    public String getAllVendedores(Model model) {
        List<VendedorDTO> vendedores = vendedorService.findAll();
        model.addAttribute("vendedores", vendedores);
        return "vendedores";
    }

    @GetMapping("/api/vendedor")
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

    @GetMapping("/api/vendedor/nombre")
    public String getVendedor(@RequestParam final String nombre, Model model) {
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

    @PostMapping
    public String createVendedor(@ModelAttribute @Valid final VendedorDTO vendedorDTO, Model model) {
        Integer createdId = vendedorService.create(vendedorDTO);
        model.addAttribute("createdId", createdId);
        return "vendedorCreado";
    }

    @PutMapping("/api/vendedor")
    public String updateVendedor(@RequestParam final Integer id,
            @ModelAttribute @Valid final VendedorDTO vendedorDTO, Model model) {
        vendedorService.update(id, vendedorDTO);
        model.addAttribute("id", id);
        return "vendedorActualizado";
    }

    @DeleteMapping("/api/vendedor")
    public String deleteVendedor(@RequestParam final Integer id, Model model) {
        final ReferencedWarning referencedWarning = vendedorService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        vendedorService.delete(id);
        return "vendedorEliminado";
    }

}
