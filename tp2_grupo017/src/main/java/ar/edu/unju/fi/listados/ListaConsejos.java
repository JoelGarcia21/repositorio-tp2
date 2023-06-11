package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.models.Consejos;

/**
 * Componente que almacena y gestiona una lista de consejos.
 */
@Component
public class ListaConsejos {

	private List<Consejos> consejos;
	
    public ListaConsejos() {
        consejos = new ArrayList<>();
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
