package ar.edu.unju.fi.controllers;

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
import ar.edu.unju.fi.services.IEmpleadoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    @Qualifier("empleadoServiceMysql")
    private IEmpleadoService empleadoService;

    @GetMapping("/gestion")
    public String getEmpleadoPage(Model model){
        model.addAttribute("titulo", "Empleados");
        model.addAttribute("tituloForm", "Datos empleado");
        model.addAttribute("empleados", empleadoService.getEmplados());
        model.addAttribute("empleado", empleadoService.getEmpleado());
        return "empleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute(name = "empleado")Empleado empleado){
        empleado.setEstado(true);
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados/gestion";
    }

    @GetMapping("/editar/{codigo}")
    public ModelAndView editarEmpleado(@PathVariable(value = "codigo")Long codigo){
        Empleado empleadoEncontrado= empleadoService.getEmpleadoByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("empleado");
        modelAndView.addObject("titulo", "Editar");
        modelAndView.addObject("tituloForm", "Editar empleado");
        modelAndView.addObject("empleado", empleadoEncontrado);
        return modelAndView;
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarEmpleado(@PathVariable(value = "codigo")Long codigo){
        empleadoService.eliminarEmpleadoByCodigo(codigo);
        return "redirect:/empleados/gestion";
    }
}
