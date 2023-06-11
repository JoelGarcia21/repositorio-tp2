package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.models.Paseador;
import ar.edu.unju.fi.services.IPaseadorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/paseos")
public class ServicioPaseosController {
    private String titulo;
    private boolean nuevo;
    private String textoBoton;
    
    
    @Autowired
    private IPaseadorService paseadorService;

    @GetMapping("")
    public ModelAndView getServiciosPaseosPage(){
        ModelAndView modelAndView = new ModelAndView("servicios_paseos");
        this.titulo = "Servicios de Paseos";
        modelAndView.addObject("titulo", this.titulo);
        return modelAndView; 
    }

    @GetMapping("/paseadores")
    public ModelAndView getPaseadoresPage(){
        ModelAndView modelAndView = new ModelAndView("paseadores");
        this.titulo = "Paseadores";
        modelAndView.addObject("listado", this.paseadorService.getListado());
        modelAndView.addObject("titulo", this.titulo);
        return modelAndView;
    }

    @GetMapping("/paseadores/nuevo")
    public ModelAndView getPaseadorNuevoPage(){
        String tituloForm = "Registro Nuevo Paseador";
        ModelAndView modelAndView = new ModelAndView("nuevo_paseador");
        this.titulo = "Nuevo Paseador";
        this.nuevo = true;
        this.textoBoton = "Guardar";
        
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("tituloFormulario", tituloForm);
        modelAndView.addObject("paseador", this.paseadorService.getPaseador());
        modelAndView.addObject("nuevo", this.nuevo);
        modelAndView.addObject("textoBoton", this.textoBoton);
        return modelAndView;
    }

    @PostMapping("/paseadores/guardar")
    public String guardarPaseador(@Valid @ModelAttribute(name = "paseador")Paseador paseador, BindingResult result, Model model){        
        
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Paseador");
            model.addAttribute("tituloFormulario", "Registro Nuevo Paseador");
            model.addAttribute("paseador", paseador);
            model.addAttribute("nuevo", true);
            model.addAttribute("textoBoton", "Guardar");
            return "nuevo_paseador";
        }
                
        this.paseadorService.guardarPaseador(paseador);
        
        return "redirect:/paseos/paseadores";
    }

    @GetMapping("/paseadores/editar/{id}")
    public ModelAndView getFormularioEmpleadoPage(@PathVariable(value = "id")int idPaseador){
        ModelAndView modelAndView = new ModelAndView("nuevo_paseador");
        String tituloForm = "Editar Datos Paseador";
        this.titulo = "Editar Paseador";
        this.nuevo = false;
        this.textoBoton = "Actualizar";
        Paseador paseadorEncontrado = this.paseadorService.getPaseadorBy(idPaseador);
        
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("tituloFormulario", tituloForm);
        modelAndView.addObject("paseador", paseadorEncontrado);
        modelAndView.addObject("nuevo", this.nuevo);
        modelAndView.addObject("textoBoton", this.textoBoton);
        return modelAndView;
        
    }

    @PostMapping("/paseadores/editar")
    public String modificarPaseador(@Valid @ModelAttribute(name = "paseador")Paseador paseador, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Paseador");
            model.addAttribute("tituloFormulario", "Editar Datos Paseador");
            model.addAttribute("paseador", paseador);
            model.addAttribute("nuevo", false);
            model.addAttribute("textoBoton", "Actualizar");
            return "nuevo_paseador";
        }
        this.paseadorService.modificarPaseador(paseador);
        return "redirect:/paseos/paseadores";
    }

    @GetMapping("paseadores/eliminar/{id}")
    public String eliminarPaseador(@ModelAttribute(value = "id")int idPaseador){
        this.paseadorService.eliminarPaseador(idPaseador);
        return "redirect:/paseos/paseadores";
    }


}
