package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.Servicio;

@Component
public class ListaServicios {
    private List<Servicio> listado;


    public ListaServicios() {
        this.listado = new ArrayList<Servicio>();
        // this.listado.add(new Servicio(1l, "Paseador", "Perez", "Juan", "Lunes", "9 a 12 - 16 a 18"));
        // this.listado.add(new Servicio(2l, "Paseador", "Díaz", "Lucas", "Lunes", "9 a 12 - 16 a 18"));
        // this.listado.add(new Servicio(3l, "Paseador", "Gomez", "Maria", "Miercoles", "9 a 12 - 16 a 18"));
        // this.listado.add(new Servicio(4l, "Paseador", "Vazquez", "Roberto", "Viernes", "9 a 12 - 16 a 18"));        
    }


    public List<Servicio> getListado() {
        return this.listado;
    }

    public void setListado(List<Servicio> listado) {
        this.listado = listado;
    }

    /**
     * Método que busca un servicio según un ID.
     * @param id tipo long
     * @return devulve un objeto Servicio si lo encuentra, sino retorna null.
     */
    public Servicio getServicio(int id){
        Servicio servicioEncontrado = null;        
        for (Servicio servicio : listado) {
            if (servicio.getId() == id) {
                servicioEncontrado = servicio;
                break;
            }
        }
        return servicioEncontrado;
    }

    /**
     * Método que busca y actualiza los datos de un servicio
     * @param servicioModificado
     */
    public void modificarPaseador(Servicio servicioModificado){
        // for (Servicio servicio : listado) {
        //     if(servicio.getId() == servicioModificado.getId()){
        //         servicio.setNombre(servicioModificado.getNombre());
        //         servicio.setApellido(servicioModificado.getApellido());
        //         servicio.setNombres(servicioModificado.getNombres());
        //         servicio.setHorario(servicioModificado.getHorario());
        //         break;
        //     }
        // }

    }

    /**
     * método que elimina un objeto Servicio de la lista según el id del Servicio.
     * @param id int
     */
    public void eliminarServicio(int id){
        for (Servicio servicio : listado) {
            if (servicio.getId() == id){
                listado.remove(servicio);
                break;
            }
        }
    }


}
