package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.models.Paseador;

public class Paseadores {
    private List<Paseador> listado;


    public Paseadores() {
        this.listado = new ArrayList<Paseador>();
    }


    public List<Paseador> getListado() {
        return this.listado;
    }

    public void setListado(List<Paseador> listado) {
        this.listado = listado;
    }


}
