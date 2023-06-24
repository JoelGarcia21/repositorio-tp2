package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.models.Servicio;

public interface IServicioService {
    
    List<Servicio> getListado();
    Servicio getServicio();
    Servicio getServicioBy(int id);
    public void guardarServicio(Servicio servcio);
    public void modificarServicio(Servicio servicioModificado);
    public void eliminarServicio(int id);


}
