package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contactenos")
public class ContactenosController {
    private String titulo;

    @GetMapping("")
    public ModelAndView getContactenosPage(){
        ModelAndView modelAndView = new ModelAndView("contactenos");
        this.titulo = "contactenos";
        modelAndView.addObject("titulo", this.titulo);
        return modelAndView; 
    }
}
