package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.models.Paseador;

public interface IPaseadorService {
    
    List<Paseador> getListado();
    Paseador getPaseador();
    Paseador getPaseadorBy(int id);
    public void guardarPaseador(Paseador paseador);
    public void modificarPaseador(Paseador paseadorModificado);
    public void eliminarPaseador(int id);


}
