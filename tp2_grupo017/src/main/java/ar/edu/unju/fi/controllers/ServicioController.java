package ar.edu.unju.fi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Empleado;
import ar.edu.unju.fi.entity.Servicio;
import ar.edu.unju.fi.services.IEmpleadoService;
import ar.edu.unju.fi.services.IServicioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    private String titulo;
    private boolean nuevo;
    private String textoBoton;

    @Autowired
    @Qualifier("servicioServiceMysql")
    private IServicioService servicioService;

    @Autowired
    @Qualifier("empleadoServiceMysql")
    private IEmpleadoService empleadoService;

    @GetMapping("/gestion")
    public ModelAndView getServiciosPaseosPage() {
        ModelAndView modelAndView = new ModelAndView("gestion_servicios");
        this.titulo = "Servicios";
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("listado", this.servicioService.getListado());
        return modelAndView;
    }

    @GetMapping("/listado")
    public ModelAndView getPaseadoresPage() {
        ModelAndView modelAndView = new ModelAndView("servicios");
        this.titulo = "Listado de servicios";
        modelAndView.addObject("listado", this.servicioService.getListado());
        modelAndView.addObject("titulo", this.titulo);
        return modelAndView;
    }

    @GetMapping("/nuevo")
    public ModelAndView getPaseadorNuevoPage() {
        String tituloForm = "Registro Nuevo servicio";
        ModelAndView modelAndView = new ModelAndView("nuevo_servicio");
        this.titulo = "Nuevo Servicio";
        this.nuevo = true;
        this.textoBoton = "Guardar";

        List<Empleado> listado = this.empleadoService.getEmpleadoByEstadoAndAsignado(true, false);
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("tituloFormulario", tituloForm);
        modelAndView.addObject("servicio", this.servicioService.getServicio());
        modelAndView.addObject("nuevo", this.nuevo);
        modelAndView.addObject("textoBoton", this.textoBoton);
        modelAndView.addObject("empleados", listado);
        
        return modelAndView;
    }

    @PostMapping("/guardar") 
    public String guardarServicio(@Valid @ModelAttribute("servicio")Servicio servicio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Empleado> listado = this.empleadoService.getEmpleadoByEstadoAndAsignado(true, false);
            model.addAttribute("titulo", "Nuevo Servicio");
            model.addAttribute("tituloFormulario", "Registro Nuevo Servicio");
            model.addAttribute("servicio", servicio);
            model.addAttribute("nuevo", true);
            model.addAttribute("textoBoton", "Guardar");
            model.addAttribute("empleados", listado);
            return "nuevo_servicio";
        }
         

        // para editar
        if (servicio.getId() != null) {
            Empleado empleadoServicioEncontrado = servicioService.getServicioById(servicio.getId()).getEmpleado();
            empleadoServicioEncontrado.setAsignado(false);
            empleadoService.guardarEmpleado(empleadoServicioEncontrado);
        }

        // -------------------------------------------------------------------------

        if (servicio.getEmpleado().getCodigo() != 0) {
            Empleado empleado = empleadoService.getEmpleadoByCodigo(servicio.getEmpleado().getCodigo());
            servicio.setEmpleado(empleado);
            servicio.getEmpleado().setAsignado(true);
            servicio.setEstado(true);

        }

        servicioService.guardarServicio(servicio);
        return "redirect:/servicio/gestion";
    }
    

    @GetMapping("/editar/{id}")  
    public ModelAndView getFormularioEmpleadoPage(@PathVariable(value = "id")Long idServicio){
        ModelAndView modelAndView = new ModelAndView("nuevo_servicio");
        String tituloForm = "Editar Datos Servicio";
        this.titulo = "Editar Servicio";
        this.nuevo = false;
        this.textoBoton = "Actualizar";

        Servicio servicioBuscado = servicioService.getServicioById(idServicio);
        
        List<Empleado> listado = empleadoService.getEmpleadoByEstadoAndAsignado(true, false);
        Empleado empleadoSevicio = empleadoService.getEmpleadoByCodigo(servicioBuscado.getEmpleado().getCodigo());
        listado.add(empleadoSevicio);

        modelAndView.addObject("empleadoVacio", listado.isEmpty());
        modelAndView.addObject("empleados", listado);
        modelAndView.addObject("titulo", this.titulo);
        modelAndView.addObject("tituloFormulario", tituloForm);
        modelAndView.addObject("servicio", servicioBuscado);
        modelAndView.addObject("nuevo", this.nuevo);
        return modelAndView;

        
    }

    @PostMapping("/editar") 
             
    public String modificarServicio(@Valid @ModelAttribute(name = "servicio")Servicio servicio, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Paseador");
            model.addAttribute("tituloFormulario", "Editar Datos Paseador");
            model.addAttribute("paseador", servicio);
            model.addAttribute("nuevo", false);
            model.addAttribute("textoBoton", "Actualizar");
            return "nuevo_servicio";
        }
        this.servicioService.modificarServicio(servicio);
        return "redirect:/servicio/gestion";
    }

    @GetMapping("/eliminar/{id}")  
    public String eliminarPaseador(@ModelAttribute(value = "id")Long idServicio){
        // volver a poner disponible al empleado
        Empleado empleadoEncontrado = servicioService.getServicioById(idServicio).getEmpleado();
        empleadoEncontrado.setAsignado(false);
        empleadoService.guardarEmpleado(empleadoEncontrado);
        // se elimina el servicio
        servicioService.eliminarServicio(idServicio);
        return "redirect:/servicios/gestion";
    }


}