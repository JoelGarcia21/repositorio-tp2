package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.models.Consejos;

public interface IConsejoService {

	List<Consejos> listarConsejos();
    void guardarConsejo(Consejos consejo);
    void modificarConsejo(int indice, Consejos nuevoConsejo);
    void eliminarConsejo(int indice);
}
