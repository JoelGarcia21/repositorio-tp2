package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.entity.Consejo;
/**
 * Esta interfaz proporciona los métodos para administrar los consejos.
 * Permite listar, guardar, modificar y eliminar consejos.
 */

public interface IConsejoService {

	List<Consejo> listarConsejos();
    void guardarConsejo(Consejo consejo);
    void modificarConsejo(int indice, Consejo nuevoConsejo);
    void eliminarConsejo(int indice);
}
