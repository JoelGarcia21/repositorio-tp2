package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.models.Paseador;

@Component
public class Paseadores {
    private List<Paseador> listado;


    public Paseadores() {
        this.listado = new ArrayList<Paseador>();
        this.listado.add(new Paseador(1, "Perez", "Juan", "9 a 12 - 16 a 18"));
        this.listado.add(new Paseador(2, "Díaz", "Lucas", "9 a 12 - 16 a 18"));
        this.listado.add(new Paseador(3, "Gomez", "Maria", "9 a 12 - 16 a 18"));
        this.listado.add(new Paseador(4, "Vazquez", "Roberto", "9 a 12 - 16 a 18"));        
    }


    public List<Paseador> getListado() {
        return this.listado;
    }

    public void setListado(List<Paseador> listado) {
        this.listado = listado;
    }

    /**
     * Método que busca un paseador según un ID.
     * @param id tipo int
     * @return devulve un objeto Paseador si lo encuentra, sino retorna null.
     */
    public Paseador getPaseador(int id){
        Paseador paseadorEncontrado = null;        
        for (Paseador paseador : listado) {
            if (paseador.getId() == id) {
                paseadorEncontrado = paseador;
                break;
            }
        }
        return paseadorEncontrado;
    }

    /**
     * Método que busca y actualiza los datos de un paseador
     * @param paseadorModificado
     */
    public void modificarPaseador(Paseador paseadorModificado){
        for (Paseador paseador : listado) {
            if(paseador.getId() == paseadorModificado.getId()){
                paseador.setApellido(paseadorModificado.getApellido());
                paseador.setNombres(paseadorModificado.getNombres());
                paseador.setHorario(paseadorModificado.getHorario());
                break;
            }
        }

    }

    /**
     * método que elimina un objeto Paseador de la lista según el id del Paseador.
     * @param id int
     */
    public void eliminarPaseador(int id){
        for (Paseador paseador : listado) {
            if (paseador.getId() == id){
                listado.remove(paseador);
                break;
            }
        }
    }


}
