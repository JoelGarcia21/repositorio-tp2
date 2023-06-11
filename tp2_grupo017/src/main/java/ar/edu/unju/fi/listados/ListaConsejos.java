package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.models.Consejos;

public class ListaConsejos {

	private List<Consejos> consejos;
	
    public ListaConsejos() {
        consejos = new ArrayList<>();
    }

    public List<Consejos> getConsejos() {
        return consejos;
    }

    public void agregarConsejo(Consejos consejo) {
        consejos.add(consejo);
    }

    public void editarConsejo(int indice, Consejos nuevoConsejo) {
        if (indice >= 0 && indice < consejos.size()) {
            consejos.set(indice, nuevoConsejo);
        }
    }

    public void eliminarConsejo(int indice) {
        if (indice >= 0 && indice < consejos.size()) {
            consejos.remove(indice);
        }
    }
}
