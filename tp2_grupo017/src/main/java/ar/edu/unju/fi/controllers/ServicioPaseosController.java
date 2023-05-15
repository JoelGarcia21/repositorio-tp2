package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/paseos")
public class ServicioPaseosController {
    private String titulo;

    @GetMapping("")
    public ModelAndView getServiciosPaseosPage(){
        ModelAndView modelAndView = new ModelAndView("servicios_paseos");
        this.titulo = "Servicios de Paseos";
        modelAndView.addObject("titulo", this.titulo);
        return modelAndView; 
    }
}
