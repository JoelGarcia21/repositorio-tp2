package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.listados.Paseadores;
import ar.edu.unju.fi.models.Paseador;

@Controller
@RequestMapping("/paseos")
public class ServicioPaseosController {
    private String titulo;
    private boolean nuevo;
    private String textoBoton;
    private Paseador paseador;
    
    private Paseadores paseadores = new Paseadores();

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
        modelAndView.addObject("listado", this.paseadores.getListado());
        return modelAndView;
    }

    @GetMapping("/paseadores/nuevo")
    public ModelAndView getPaseadorNuevoPage(){
        String tituloForm = "Registro Nuevo Paseador";
        ModelAndView modelAndView = new ModelAndView("nuevo_paseador");
        this.titulo = "Nuevo Paseador";
        this.nuevo = true;
        this.textoBoton = "Guardar";
        this.paseador = new Paseador();
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("tituloFormulario", tituloForm);
        modelAndView.addObject("paseador", this.paseador);
        modelAndView.addObject("nuevo", this.nuevo);
        modelAndView.addObject("textoBoton", this.textoBoton);
        return modelAndView;
    }

    @PostMapping("/paseadores/guardar")
    public String guardarPaseador(@ModelAttribute(name = "paseador")Paseador paseador){        
        int id;
        if (this.paseadores.getListado().isEmpty()) {
            paseador.setId(1);
        } else {
            int ultimoId = this.paseadores.getListado().get(this.paseadores.getListado().size()-1).getId();
            id = ultimoId+1;
            paseador.setId(id);
        }
        this.paseadores.getListado().add(paseador);
        
        return "redirect:/paseos/paseadores";
    }

    @GetMapping("/paseadores/editar/{id}")
    public ModelAndView getFormularioEmpleadoPage(@PathVariable(value = "id")int idPaseador){
        ModelAndView modelAndView = new ModelAndView("nuevo_paseador");
        String tituloForm = "Editar Datos Paseador";
        this.titulo = "Editar Paseador";
        this.nuevo = false;
        this.textoBoton = "Actualizar";
        Paseador paseadorEncontrado = this.paseadores.getPaseador(idPaseador);
        
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("tituloFormulario", tituloForm);
        modelAndView.addObject("paseador", paseadorEncontrado);
        modelAndView.addObject("nuevo", this.nuevo);
        modelAndView.addObject("textoBoton", this.textoBoton);
        return modelAndView;
        
    }

    @PostMapping("/paseadores/editar")
    public String modificarPaseador(@ModelAttribute(name = "paseador")Paseador paseador){
        this.paseadores.modificarPaseador(paseador);
        return "redirect:/paseos/paseadores";
    }

    @GetMapping("paseadores/eliminar/{id}")
    public String eliminarPaseador(@ModelAttribute(value = "id")int idPaseador){
        this.paseadores.eliminarPaseador(idPaseador);
        return "redirect:/paseos/paseadores";
    }


}
