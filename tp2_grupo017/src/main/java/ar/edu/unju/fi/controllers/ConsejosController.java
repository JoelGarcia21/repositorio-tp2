package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.services.IConsejoService;

import java.util.List;
/**
 * Controlador que gestiona las operaciones relacionadas con los consejos.
 */

@Controller
@RequestMapping("/consejos")
public class ConsejosController {
	@Autowired
	@Qualifier("consejoServiceMysqulImp") //La anotación @Qualifier se utiliza para especificar cuál implementación concreta de una interfaz se debe utilizar cuando hay múltiples implementaciones disponibles.
	private IConsejoService consejoService;
	
	/**
	 * Constructor que inyecta la dependencia de IConsejoService.
	 * @param consejoService Servicio de consejos
	 */
    public ConsejosController(IConsejoService consejoService) {
    	this.consejoService = consejoService;
    }
    
    /**
     * Maneja la solicitud GET para mostrar los consejos.
     * @param model Modelo para pasar los datos a la vista
     * @return La vista "creacion_consejos_de_salud" que muestra la lista de consejos
     */
    @GetMapping
    public String mostrarConsejos(Model model) {
        List<Consejo> consejos = consejoService.listarConsejos();
        model.addAttribute("consejos", consejos);
        return "creacion_consejos_de_salud";
    }

    /**
     * Maneja la solicitud GET para mostrar el formulario de nuevo consejo.
     * @return La vista "nuevo_consejo" para agregar un nuevo consejo
     */
    @GetMapping("/nuevo_consejo")
    public String nuevoConsejo() {
    	
    	return "nuevo_consejo";
    }
    
    /**
     * Maneja la solicitud POST para agregar un nuevo consejo.
     * @param texto El texto del consejo a agregar
     * @return Redirecciona a la página de consejos después de agregar el consejo
     */
    @PostMapping("/agregar")
    public String agregarConsejo(@RequestParam("consejo") String texto) {
        Consejo nuevoConsejo = new Consejo(texto);
        consejoService.guardarConsejo(nuevoConsejo);
        return "redirect:/consejos";
    }
    
    /*@GetMapping("/editar/{indice}")
    public String editarConsejo(@PathVariable("indice") int indice, Model model) {
        List<Consejos> consejos = listaConsejos.getConsejos();

        if (indice >= 0 && indice < consejos.size()) {
            Consejos consejo = consejos.get(indice);
            model.addAttribute("consejo", consejo);
        }

        return "nuevo_consejo";
    }*/
    
    /**
     * Maneja la solicitud GET para eliminar un consejo.
     * @param indice El índice del consejo a eliminar
     * @return Redirecciona a la página de consejos después de eliminar el consejo
     */
    @GetMapping("/eliminar/{indice}")
    public String eliminarConsejo(@PathVariable("indice") int indice) {
    	consejoService.eliminarConsejo(indice);
        return "redirect:/consejos";
    }
}
