package ar.edu.unju.fi.controllers;

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

import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.services.IConsejoService;
import jakarta.validation.Valid;

import java.util.List;
/**
 * Controlador que gestiona las operaciones relacionadas con los consejos.
 */

@Controller
@RequestMapping("/consejos")
public class ConsejoController {
	@Autowired
	@Qualifier("consejoServiceMysqulImp") //La anotación @Qualifier se utiliza para especificar cuál implementación concreta de una interfaz se debe utilizar cuando hay múltiples implementaciones disponibles.
	private IConsejoService consejoService;

    private static final Log LOGGER = LogFactory.getLog(ConsejoController.class);
	
	/**
	 * Constructor que inyecta la dependencia de IConsejoService.
	 * @param consejoService Servicio de consejos
	 */
    public ConsejoController(IConsejoService consejoService) {
    	this.consejoService = consejoService;
    }
    
    /**
     * Maneja la solicitud GET para mostrar los consejos.
     * @param model Modelo para pasar los datos a la vista
     * @return La vista "creacion_consejos_de_salud" que muestra la lista de consejos
     */
    @GetMapping("/gestion")
    public String mostrarConsejos(Model model) {
        List<Consejo> consejos = consejoService.listarConsejos();
        model.addAttribute("consejos", consejos);
        model.addAttribute("titulo", "Gestion | Consejos de Salud");
        return "creacion_consejos_de_salud";
    }

    /**
     * Método que muestra los consejos de salud en la página para el usuario.
     * @param model
     * @return la vista "consejos_salud".
     */
    @GetMapping("/listado")
    public String mostrarConsejosPage(Model model) {
        model.addAttribute("titulo", "Consejos de Salud");
        model.addAttribute("consejos", consejoService.listarConsejos());
        return "consejos_salud";

    }

    /**
     * Maneja la solicitud GET para mostrar el formulario de nuevo consejo.
     * @return La vista "nuevo_consejo" para agregar un nuevo consejo
     */
    @GetMapping("/nuevo_consejo")
    public String nuevoConsejo(Model model) {
        model.addAttribute("titulo", "Nuevo Consejo");
    	model.addAttribute("consejo", consejoService.getConsejo());
        model.addAttribute("edicion", false);
    	return "nuevo_consejo";
    }
    
    /**
     * Maneja la solicitud POST para agregar un nuevo consejo.
     * @param texto El texto del consejo a agregar
     * @return Redirecciona a la página de consejos después de agregar el consejo
     */
    @PostMapping("/agregar")
    public String agregarConsejo(@Valid @ModelAttribute("consejo")Consejo consejo, BindingResult result, Model model) {
        // Consejo nuevoConsejo = new Consejo(texto);
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Consejo");
            model.addAttribute("consejo", consejo);
            model.addAttribute("edicion", false);
            return "nuevo_consejo";
        }

        consejoService.guardarConsejo(consejo);
        return "redirect:/consejos/gestion";
    }

    /**
     * Método que busca el objeto consejo según su id y lo envia al formulario para editarlo
     * 
     * @param id de tipo Long que representa el id del consejo a editar.
     * @param model que se enviará al la vista con el consejo.
     * @return un string que es la página de nuevo_consejo".
     */
    @GetMapping("/editar/{id}")
    public String editarConsejoPage(@PathVariable(value = "id")Long id, Model model){
        Consejo consejoBuscado = consejoService.getByIdConsejo(id);
        model.addAttribute("titulo", "Editar Consejo");
        model.addAttribute("consejo", consejoBuscado);
        model.addAttribute("edicion", true);
        LOGGER.info("valor del estado-------->"+consejoBuscado.getEstado());
        return "nuevo_consejo";
    }
    
    
    /**
     * Método que guarda el objeto consejo modificado.
     * 
     * @param consejo objeto del tipo Consejo.
     * @param result objeto usuado para determinar si hay error de validacion.
     * @param model 
     * @return a la pagina de gestion.
     */
    @PostMapping("/editar")
    public String editar(@Valid @ModelAttribute("consejo")Consejo consejo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Consejo");
            model.addAttribute("consejo", consejo);
            model.addAttribute("edicion", true);
            return "nuevo_consejo";
        }

        LOGGER.info("vlaor dle id:------------------->"+consejo.getId());
        LOGGER.info("valor del objeto consejo estado:---------->"+consejo.getEstado());
        consejo.setEstado(true);
        consejoService.modificarConsejo(consejo);
        return "redirect:/consejos/gestion";

    }
    
    
    
    
    
    /**
     * Maneja la solicitud GET para eliminar un consejo.
     * @param indice El índice del consejo a eliminar
     * @return Redirecciona a la página de consejos después de eliminar el consejo
     */
    @GetMapping("/eliminar/{indice}")
    public String eliminarConsejo(@PathVariable("indice") Long indice) {
    	consejoService.eliminarConsejo(indice);
        return "redirect:/consejos/gestion";
    }
}
