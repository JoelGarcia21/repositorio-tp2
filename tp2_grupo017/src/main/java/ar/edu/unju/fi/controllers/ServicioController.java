package ar.edu.unju.fi.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Empleado;
import ar.edu.unju.fi.entity.Servicio;
import ar.edu.unju.fi.services.IEmpleadoService;
import ar.edu.unju.fi.services.IServicioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    private static final Log LOGGER = LogFactory.getLog(ServicioController.class);

    @Autowired
    @Qualifier("servicioServiceMysql")
    private IServicioService servicioService;

    @Autowired
    @Qualifier("empleadoServiceMysql")
    private IEmpleadoService empleadoService;

    @GetMapping("/gestion")
    public String getServicioPage(Model model) {
        model.addAttribute("titulo", "Gestión Servicios");
        model.addAttribute("servicios", this.servicioService.getServicioByEstado(true));
        return "gestion_servicios";
    }

    @GetMapping("/listado")
    public String getServicioPagePrincipal(Model model) {
        model.addAttribute("titulo", "Servicios");
        model.addAttribute("servicios", servicioService.getServicioByEstado(true));
        return "servicios";
    }

    @GetMapping("/nuevo")
    public String getNuevoServicio(Model model) {
        List<Empleado> listado = this.empleadoService.getEmpleadoByEstadoAndAsignado(true, false);
        model.addAttribute("titulo", "Nuevo Servicio");
        model.addAttribute("tituloForm", "Registro nuevo servicio");
        model.addAttribute("servicio", this.servicioService.getServicio());
        model.addAttribute("empleadoVacio", listado.isEmpty());
        model.addAttribute("nuevo", true);
        model.addAttribute("textoBoton", "Guardar");
        model.addAttribute("empleados", listado);
        return "nuevo_servicio";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@Valid @ModelAttribute("servicio") Servicio servicio, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            LOGGER.info("se produjo un error------------------->"+result.hasErrors());
            List<Empleado> listado = this.empleadoService.getEmpleadoByEstadoAndAsignado(true, false);
            model.addAttribute("titulo", "Nuevo Servicio");
            model.addAttribute("tituloForm", "Registro nuevo servicio");
            model.addAttribute("servicio", servicio);
            model.addAttribute("empleadoVacio", listado.isEmpty());
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
            LOGGER.info("valor del codigo del empleado:-------------------->"+servicio.getEmpleado().getCodigo());
            Empleado empleado = empleadoService.getEmpleadoByCodigo(servicio.getEmpleado().getCodigo());
            servicio.setEmpleado(empleado);
            servicio.getEmpleado().setAsignado(true);
            servicio.setEstado(true);

        }

        LOGGER.info("valor del codigo del empleadod:------------>"+servicio.getEmpleado().getCodigo());
        LOGGER.info("valor del estado----------------->:"+servicio.getEstado());
        servicioService.guardarServicio(servicio);
        return "redirect:/servicio/gestion";

        // servicio.setEstado(true);

        // LOGGER.info("nombre dle servicio:------------------------->" +
        // servicio.getNombre());
        // LOGGER.info("horario dle servicio:------------------------->" +
        // servicio.getHorario());
        // LOGGER.info("dia dle servicio:------------------------->" +
        // servicio.getDia());

        // servicioService.guardarServicio(servicio);
        // return "redirect:/servicio/gestion";
    }

    @GetMapping("/editar/{codigo}")
    public String editarServicio(@PathVariable(value = "codigo") Long codigo, Model model) {
        Servicio servicioBuscado = servicioService.getServicioById(codigo);
        model.addAttribute("titulo", "Editar Servicio");
        model.addAttribute("tituloForm", "Editar servicio");
        model.addAttribute("servicio", servicioBuscado);
        model.addAttribute("textoBoton", "Editar");
        model.addAttribute("nuevo", false);
        List<Empleado> listado = empleadoService.getEmpleadoByEstadoAndAsignado(true, false);
        Empleado empleadoSevicio =
        empleadoService.getEmpleadoByCodigo(servicioBuscado.getEmpleado().getCodigo());
        listado.add(empleadoSevicio);
        model.addAttribute("empleadoVacio", listado.isEmpty());
        model.addAttribute("empleados", listado);
        return "nuevo_servicio";

    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarServicio(@PathVariable(value = "codigo") Long codigo) {
        // volver a poner disponible al empleado
        Empleado empleadoEncontrado =servicioService.getServicioById(codigo).getEmpleado();
        empleadoEncontrado.setAsignado(false);
        empleadoService.guardarEmpleado(empleadoEncontrado);
        // se elimina el servicio
        servicioService.eliminarServicio(codigo);
        return "redirect:/servicio/gestion";

    }

    @GetMapping("/buscar")
    public ModelAndView buscarByDia(@RequestParam(value = "dia", required = false) String dia) {
        ModelAndView modelAndView = new ModelAndView("servicios");
        modelAndView.addObject("titulo", "Servicios");
        modelAndView.addObject("servicios", servicioService.getServiciosByDia(dia));
        return modelAndView;
    }

    @GetMapping("/")
    public String getServiciosPage(Model model) {
        model.addAttribute("titulo", "Servicios");
        model.addAttribute("servicios", servicioService.getServicioByEstado(true));
        return "servicios";
    }

}