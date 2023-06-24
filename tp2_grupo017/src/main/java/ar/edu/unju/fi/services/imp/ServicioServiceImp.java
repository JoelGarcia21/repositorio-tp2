package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.listados.ListaServicios;
import ar.edu.unju.fi.models.Servicio;
import ar.edu.unju.fi.services.IServicioService;

@Service
public class ServicioServiceImp implements IServicioService {
    
    @Autowired
    private ListaServicios servicios;

    @Autowired
    private Servicio servicio;

    
    /** 
     * Método que retorna el listado de servicios.
     * @return List<Servicio>
     */
    @Override
    public List<Servicio> getListado() {
        return this.servicios.getListado();
    }

    
    
    /** 
     * Método que retorna un objeto del tipo Servicio.
     * @return Paseador
     */
    @Override
    public Servicio getServicio() {
        return this.servicio;
    }

    
    /** 
     * Método que retorna un objeto del tipo Servicio,según un nro. 
     * de ID  
     * @param id de tipo int
     * @return Servicio
     */
    @Override
    public Servicio getServicioBy(int id) {
        Servicio servicioEncontrado = null;        
        for (Servicio servicio : this.servicios.getListado()) {
            if (servicio.getId() == id) {
                servicioEncontrado = servicio;
                break;
            }
        }
        return servicioEncontrado;
    }

    
    /** 
     * Método que guarda un objeto del tipo Servicio en una lista. 
     * @param servicio del tipo Servicio
     */
    @Override
    public void guardarServicio(Servicio servicio) {
        int id;
        if (this.servicios.getListado().isEmpty()) {
            servicio.setId(1);
        } else {
            int ultimoId = this.servicios.getListado().get(this.servicios.getListado().size()-1).getId();
            id = ultimoId+1;
            servicio.setId(id);
        }

        this.servicios.getListado().add(servicio);
    }

    
    /** 
     * Método que busca y actualiza los datos de un servicio.
     * @param servicioModificado
     */
    @Override
    public void modificarServicio(Servicio servicioModificado) {
        for (Servicio servicio : this.servicios.getListado()) {
            if(servicio.getId() == servicioModificado.getId()){
                servicio.setNombre(servicioModificado.getNombre());
                servicio.setApellido(servicioModificado.getApellido());
                servicio.setNombres(servicioModificado.getNombres());
                servicio.setDia(servicioModificado.getDia());
                servicio.setHorario(servicioModificado.getHorario());
                break;
            }
        }
    }
    

    
    
    
    /** 
     * Método que elimina un objeto Paseador de la lista según el id del servicio.
     * @param id tipo int.
     */
    @Override    
    public void eliminarServicio(int id) {
        for (Servicio servicio : this.servicios.getListado()) {
            if (servicio.getId() == id){
                this.servicios.getListado().remove(servicio);
                break;
            }
        }
    }

    
}
