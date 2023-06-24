package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.entity.Servicio;

public interface IServicioService {
    
    List<Servicio> getListado();
    Servicio getServicio();
    Servicio getServicioBy(Long id);
    public void guardarServicio(Servicio servcio);
    public void modificarServicio(Servicio servicioModificado);
    public void eliminarServicio(Long id);


}
