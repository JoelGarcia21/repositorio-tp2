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

    /**
     * Método que renderiza la página empleado.
     * 
     * @param model  parammetro de tipo Model.
     * @return un string que es el nombre de la pág. html "empleado".
     */
    @GetMapping("/gestion")
    public String getEmpleadoPage(Model model) {
        model.addAttribute("titulo", "Empleados");
        model.addAttribute("tituloForm", "Datos empleado");
        model.addAttribute("empleados", empleadoService.getEmpleadosByEstado(true));
        model.addAttribute("empleado", empleadoService.getEmpleado());
        return "empleado";
    }

    
    /**
     * Método que guarda un objeto del tipo Empleado, previamente verificando si el objeto es válido.
     * Si el empleado tiene campos invalidos retorna a la pág del formulario.
     * 
     * @param empleado parametro del tipo Empleado traido por @ModelAttribute de la vista.
     * @Valid indica que el objeto podría ser validado usando las reglas de validacion de la clase Empleado.
     * @param result el parametro de tipo BindingResult usado para almacenar mostrar los errores de validación.
     * @param model parametro del tipo Model usado para enviar datos entre el controller y la vista.
     * @return un String, si hay errores de validación retorna al formualrio de empleado, sino redirecciona a la 
     * vista de gestión de empleados.
     */
    @PostMapping("/guardar")
    public String guardarEmpleado(@Valid @ModelAttribute(name = "empleado") Empleado empleado, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Empleados");
            model.addAttribute("tituloForm", "Datos empleado");
            model.addAttribute("empleados", empleadoService.getEmpleadosByEstado(true));
            model.addAttribute("empleado", empleado);
            return "empleado";
        }

        empleado.setEstado(true);
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados/gestion";
    }

    
    /**
     * Método que recupera un empleado por su código y retorna un objeto ModelAnView con la 
     * información de para editar.
     * 
     * @param codigo parametro del tipo Long que representa el codigo del empleado a buscar.
     * @return un objetod del tipo ModelAndView, con los datos del empleado.
     */
    @GetMapping("/editar/{codigo}")
    public ModelAndView editarEmpleado(@PathVariable(value = "codigo") Long codigo) {
        Empleado empleadoEncontrado = empleadoService.getEmpleadoByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("empleado");
        modelAndView.addObject("titulo", "Editar");
        modelAndView.addObject("tituloForm", "Editar empleado");
        modelAndView.addObject("empleado", empleadoEncontrado);
        return modelAndView;
    }

    /**
     * Método que elimina un empleado según su codigo.
     * 
     * @param codigo parametro del tipo long que representa el codigo del empleado a eliminar.
     * @return un string que es el nombre de la página de gestión de empleados.
     */
    @GetMapping("/eliminar/{codigo}")
    public String eliminarEmpleado(@PathVariable(value = "codigo") Long codigo) {
        empleadoService.eliminarEmpleadoByCodigo(codigo);
        return "redirect:/empleados/gestion";
    }
}
