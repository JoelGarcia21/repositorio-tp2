package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.Consejos;
import ar.edu.unju.fi.services.IConsejoService;
/**
 * Componente que almacena y gestiona una lista de consejos.
 */
@Component
public class ListaConsejos {

	private List<Consejos> consejos;
	
	@Autowired
	private IConsejoService consejoService;
	
    public ListaConsejos(IConsejoService consejoService) {
    	this.consejoService = consejoService;
        consejos = new ArrayList<>();
        //Se agregan consejos de ejemplo.
        consejoService.guardarConsejo(new Consejos("Mantén a tu mascota al día con sus vacunas: Es importante que tu mascota esté al día con sus vacunas para protegerla contra enfermedades peligrosas. Consulta con tu veterinario para determinar qué vacunas son necesarias para tu mascota."));
        consejoService.guardarConsejo(new Consejos("Dale una dieta equilibrada: Una dieta equilibrada es esencial para la salud de tu mascota. Asegúrate de proporcionarle una alimentación adecuada y evitar darle alimentos que no sean adecuados para su especie."));
        consejoService.guardarConsejo(new Consejos("Evita productos tóxicos: Muchos productos del hogar, como plantas y productos químicos, pueden ser tóxicos para las mascotas. Mantén estos productos fuera del alcance de tu mascota para evitar cualquier peligro."));
        consejoService.guardarConsejo(new Consejos("Monitorea su salud dental: La salud dental es importante para la salud general de tu mascota. Asegúrate de cepillarle los dientes regularmente y proporcionarle juguetes adecuados para ayudar a mantener sus dientes limpios y sanos."));
        consejoService.guardarConsejo(new Consejos("Presta atención a su comportamiento: Tu mascota no puede decirte cuándo algo no está bien, por lo que es importante prestar atención a su comportamiento y detectar cualquier cambio que pueda indicar un problema de salud. Si notas algún cambio en el comportamiento de tu mascota, consulta a tu veterinario."));
        consejoService.guardarConsejo(new Consejos("Proporciona un ambiente seguro: Mantén a tu mascota en un ambiente seguro y protegido, lejos de peligros potenciales como calles transitadas, cuerpos de agua y productos químicos peligrosos."));
    }

    public List<Consejos> getConsejos() {
        return consejos;
    }

    /**
     * Agrega un consejo a la lista.
     * @param consejo El consejo a agregar
     */
    public void agregarConsejo(Consejos consejo) {
        consejos.add(consejo);
    }

    /**
     * Edita un consejo existente en la lista.
     * @param indice El índice del consejo a editar
     * @param nuevoConsejo El nuevo consejo que reemplazará al existente
     */
    public void editarConsejo(int indice, Consejos nuevoConsejo) {
        if (indice >= 0 && indice < consejos.size()) {
            consejos.set(indice, nuevoConsejo);
        }
    }

    /**
     * Elimina un consejo de la lista.
     * @param indice El índice del consejo a eliminar
     */
    public void eliminarConsejo(int indice) {
        if (indice >= 0 && indice < consejos.size()) {
            consejos.remove(indice);
        }
    }
}
