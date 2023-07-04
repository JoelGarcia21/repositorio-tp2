package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.entity.Provincia;
import ar.edu.unju.fi.services.IProvinciaService;

@Controller
@RequestMapping("/provincia")
public class ProvinciaController {
    
    @Autowired
    @Qualifier("provinciaServiceMysql")
    private IProvinciaService provinciaService;

    @GetMapping("/gestion")
    public String getProvinciaGestionPage(Model model) {
        model.addAttribute("titulo", "Provincias");
        model.addAttribute("tituloForm", "Registro Provincia");
        model.addAttribute("provincia", this.provinciaService.getProvincia());
        model.addAttribute("provincias", this.provinciaService.getByEstado(true));
        return "gestion_provincias";
    }
    
    @PostMapping("/guardar")
    public String guardarProvincia(@ModelAttribute("provincia")Provincia provincia){
        provincia.setEstado(true);
        this.provinciaService.guardarProvincia(provincia);
        return "redirect:/provincia/gestion";
    }

    @GetMapping("/editar/{codigo}")
    public String getEditarPage(@PathVariable(value = "codigo")Long codigo, Model model) {
        Provincia provinciaBuscada = this.provinciaService.getByCodigo(codigo);
        model.addAttribute("titulo", "Provincias");
        model.addAttribute("tituloForm", "Registro Provincia");
        model.addAttribute("provincia", provinciaBuscada);
        model.addAttribute("provincias", this.provinciaService.getByEstado(true));
        return "gestion_provincias";
    }

    @GetMapping("/eliminar/{codigo}")
    public String getEliminar(@PathVariable(value = "codigo")Long codigo) {
        this.provinciaService.eliminarProvinciaByCodigo(codigo);
        
        return "redirect:/provincia/gestion";
    }
}
