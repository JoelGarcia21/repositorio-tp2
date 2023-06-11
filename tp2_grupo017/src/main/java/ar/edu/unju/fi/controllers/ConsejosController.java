package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.listados.ListaConsejos;
import ar.edu.unju.fi.models.Consejos;

import java.util.List;

@Controller
@RequestMapping("/consejos")
public class ConsejosController {
    private ListaConsejos listaConsejos;

    public ConsejosController() {
        listaConsejos = new ListaConsejos();
        listaConsejos.agregarConsejo(new Consejos("Mantén a tu mascota al día con sus vacunas: Es importante que tu mascota esté al día con sus vacunas para protegerla contra enfermedades peligrosas. Consulta con tu veterinario para determinar qué vacunas son necesarias para tu mascota."));
        listaConsejos.agregarConsejo(new Consejos("Dale una dieta equilibrada: Una dieta equilibrada es esencial para la salud de tu mascota. Asegúrate de proporcionarle una alimentación adecuada y evitar darle alimentos que no sean adecuados para su especie."));
        listaConsejos.agregarConsejo(new Consejos("Evita productos tóxicos: Muchos productos del hogar, como plantas y productos químicos, pueden ser tóxicos para las mascotas. Mantén estos productos fuera del alcance de tu mascota para evitar cualquier peligro."));
        listaConsejos.agregarConsejo(new Consejos("Monitorea su salud dental: La salud dental es importante para la salud general de tu mascota. Asegúrate de cepillarle los dientes regularmente y proporcionarle juguetes adecuados para ayudar a mantener sus dientes limpios y sanos."));
        listaConsejos.agregarConsejo(new Consejos("Presta atención a su comportamiento: Tu mascota no puede decirte cuándo algo no está bien, por lo que es importante prestar atención a su comportamiento y detectar cualquier cambio que pueda indicar un problema de salud. Si notas algún cambio en el comportamiento de tu mascota, consulta a tu veterinario."));
        listaConsejos.agregarConsejo(new Consejos("Proporciona un ambiente seguro: Mantén a tu mascota en un ambiente seguro y protegido, lejos de peligros potenciales como calles transitadas, cuerpos de agua y productos químicos peligrosos."));
    }

    @GetMapping
    public String mostrarConsejos(Model model) {
        List<Consejos> consejos = listaConsejos.getConsejos();
        model.addAttribute("consejos", consejos);
        return "creacion_consejos_de_salud";
    }

    
    @GetMapping("/nuevo_consejo")
    public String nuevoConsejo() {
    	
    	return "nuevo_consejo";
    }
    
    
    @PostMapping("/agregar")
    public String agregarConsejo(@RequestParam("consejo") String texto) {
        Consejos nuevoConsejo = new Consejos(texto);
        listaConsejos.agregarConsejo(nuevoConsejo);
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

    @GetMapping("/eliminar/{indice}")
    public String eliminarConsejo(@PathVariable("indice") int indice) {
        listaConsejos.eliminarConsejo(indice);
        return "redirect:/consejos";
    }
}
