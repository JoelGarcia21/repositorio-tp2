package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.listados.Paseadores;
import ar.edu.unju.fi.models.Paseador;
import ar.edu.unju.fi.services.IPaseadorService;

@Service
public class PaseadorServiceImp implements IPaseadorService {
    
    @Autowired
    private Paseadores paseadores;

    @Autowired
    private Paseador paseador;

    
    /** 
     * Método que retorna el listado de paseadores.
     * @return List<Paseador>
     */
    @Override
    public List<Paseador> getListado() {
        return this.paseadores.getListado();
    }

    
    
    /** 
     * Método que retorna un objeto del tipo Paseador.
     * @return Paseador
     */
    @Override
    public Paseador getPaseador() {
        return this.paseador;
    }

    
    /** 
     * Método que retorna un objeto del tipo Paseador,según un nro. 
     * de ID  
     * @param id de tipo int
     * @return Paseador
     */
    @Override
    public Paseador getPaseadorBy(int id) {
        Paseador paseadorEncontrado = null;        
        for (Paseador paseador : this.paseadores.getListado()) {
            if (paseador.getId() == id) {
                paseadorEncontrado = paseador;
                break;
            }
        }
        return paseadorEncontrado;
    }

    
    /** 
     * Método que guarda un objeto del tipo Paseador en una lista. 
     * @param paseador del tipo Paseador
     */
    @Override
    public void guardarPaseador(Paseador paseador) {
        int id;
        if (this.paseadores.getListado().isEmpty()) {
            paseador.setId(1);
        } else {
            int ultimoId = this.paseadores.getListado().get(this.paseadores.getListado().size()-1).getId();
            id = ultimoId+1;
            paseador.setId(id);
        }

        this.paseadores.getListado().add(paseador);
    }

    
    /** 
     * Método que busca y actualiza los datos de un paseador.
     * @param paseadorModificado
     */
    @Override
    public void modificarPaseador(Paseador paseadorModificado) {
        for (Paseador paseador : this.paseadores.getListado()) {
            if(paseador.getId() == paseadorModificado.getId()){
                paseador.setApellido(paseadorModificado.getApellido());
                paseador.setNombres(paseadorModificado.getNombres());
                paseador.setHorario(paseadorModificado.getHorario());
                break;
            }
        }
    }
    

    
    
    
    /** 
     * Método que elimina un objeto Paseador de la lista según el id del Paseador.
     * @param id tipo int.
     */
    @Override    
    public void eliminarPaseador(int id) {
        for (Paseador paseador : this.paseadores.getListado()) {
            if (paseador.getId() == id){
                this.paseadores.getListado().remove(paseador);
                break;
            }
        }
    }

    
}
