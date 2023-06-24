package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.Consejos;
import ar.edu.unju.fi.services.IConsejoService;

import java.util.List;
/**
 * Controlador que gestiona las operaciones relacionadas con los consejos.
 */

@Controller
@RequestMapping("/consejos")
public class ConsejosController {
	@Autowired
	private IConsejoService consejoService;
	
	/**
	 * Constructor que inyecta la dependencia de IConsejoService.
	 * @param consejoService Servicio de consejos
	 */
    public ConsejosController(IConsejoService consejoService) {
    	this.consejoService = consejoService;
    	//Se agregan consejos de ejemplo.
    	consejoService.guardarConsejo(new Consejos("Mantén a tu mascota al día con sus vacunas: Es importante que tu mascota esté al día con sus vacunas para protegerla contra enfermedades peligrosas. Consulta con tu veterinario para determinar qué vacunas son necesarias para tu mascota."));
    	consejoService.guardarConsejo(new Consejos("Dale una dieta equilibrada: Una dieta equilibrada es esencial para la salud de tu mascota. Asegúrate de proporcionarle una alimentación adecuada y evitar darle alimentos que no sean adecuados para su especie."));
    	consejoService.guardarConsejo(new Consejos("Evita productos tóxicos: Muchos productos del hogar, como plantas y productos químicos, pueden ser tóxicos para las mascotas. Mantén estos productos fuera del alcance de tu mascota para evitar cualquier peligro."));
    	consejoService.guardarConsejo(new Consejos("Monitorea su salud dental: La salud dental es importante para la salud general de tu mascota. Asegúrate de cepillarle los dientes regularmente y proporcionarle juguetes adecuados para ayudar a mantener sus dientes limpios y sanos."));
    	consejoService.guardarConsejo(new Consejos("Presta atención a su comportamiento: Tu mascota no puede decirte cuándo algo no está bien, por lo que es importante prestar atención a su comportamiento y detectar cualquier cambio que pueda indicar un problema de salud. Si notas algún cambio en el comportamiento de tu mascota, consulta a tu veterinario."));
    	consejoService.guardarConsejo(new Consejos("Proporciona un ambiente seguro: Mantén a tu mascota en un ambiente seguro y protegido, lejos de peligros potenciales como calles transitadas, cuerpos de agua y productos químicos peligrosos."));
        
    }
    
    /**
     * Maneja la solicitud GET para mostrar los consejos.
     * @param model Modelo para pasar los datos a la vista
     * @return La vista "creacion_consejos_de_salud" que muestra la lista de consejos
     */
    @GetMapping
    public String mostrarConsejos(Model model) {
        List<Consejos> consejos = consejoService.listarConsejos();
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
        Consejos nuevoConsejo = new Consejos(texto);
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
