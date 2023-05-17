package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.models.Paseador;

@Controller
@RequestMapping("/paseos")
public class ServicioPaseosController {
    private String titulo;
    private boolean nuevo;
    private Paseador paseador;

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
        return modelAndView;
    }

    @GetMapping("/paseadores/nuevo")
    public ModelAndView getPaseadorNuevoPage(){
        String tituloForm = "Registro Nuevo Paseador";
        ModelAndView modelAndView = new ModelAndView("nuevo_paseador");
        this.titulo = "Nuevo Paseador";
        this.nuevo = true;
        this.paseador = new Paseador();
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("tituloFormulario", tituloForm);
        modelAndView.addObject("paseador", this.paseador);
        modelAndView.addObject("nuevo", this.nuevo);
        return modelAndView;
    }

    
}
