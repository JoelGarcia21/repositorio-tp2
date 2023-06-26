package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.entity.Servicio;

public interface IServicioService {
    
    List<Servicio> getListado();
    List<Servicio> getServicioByEstado(boolean estado);
    Servicio getServicio();
    Servicio getServicioById(Long id);
    public void guardarServicio(Servicio servcio);
    public void modificarServicio(Servicio servicioModificado);
    public void eliminarServicio(Long id);
    public List<Servicio> getServiciosByDia(String dia);


}
