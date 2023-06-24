package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.services.IConsejoService;
/**
 * Componente que almacena y gestiona una lista de consejos.
 */
@Component
public class ListaConsejos {

	private List<Consejo> consejos;
	
	@Autowired
	@Qualifier("consejoServiceMysqulImp") //La anotación @Qualifier se utiliza para especificar cuál implementación concreta de una interfaz se debe utilizar cuando hay múltiples implementaciones disponibles.
	private IConsejoService consejoService;
	
    public ListaConsejos(IConsejoService consejoService) {
    	this.consejoService = consejoService;
        consejos = new ArrayList<>();
        //Se agregan consejos de ejemplo.
        consejoService.guardarConsejo(new Consejo("Mantén a tu mascota al día con sus vacunas."));
    	consejoService.guardarConsejo(new Consejo("Dale una dieta equilibrada: Una dieta equilibrada es esencial para la salud de tu mascota. "));
    	consejoService.guardarConsejo(new Consejo("Evita productos tóxicos: Como plantas y productos químicos, pueden ser tóxicos para las mascotas"));
    	consejoService.guardarConsejo(new Consejo("Monitorea su salud dental: La salud dental es importante para la salud general de tu mascota."));
    	consejoService.guardarConsejo(new Consejo("Presta atención a su comportamiento: Tu mascota no puede decirte cuándo algo no está bien."));
    	consejoService.guardarConsejo(new Consejo("Proporciona un ambiente seguro: Mantén a tu mascota en un ambiente seguro y protegido."));
    }

    public List<Consejo> getConsejos() {
        return consejos;
    }

    /**
     * Agrega un consejo a la lista.
     * @param consejo El consejo a agregar
     */
    public void agregarConsejo(Consejo consejo) {
        consejos.add(consejo);
    }

    /**
     * Edita un consejo existente en la lista.
     * @param indice El índice del consejo a editar
     * @param nuevoConsejo El nuevo consejo que reemplazará al existente
     */
    public void editarConsejo(int indice, Consejo nuevoConsejo) {
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
