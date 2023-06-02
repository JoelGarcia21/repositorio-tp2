package ar.edu.unju.fi.models;

import org.springframework.stereotype.Component;

@Component
public class Paseador {
    private int id;
    private String apellido;
    private String nombres;
    private String horario;


    public Paseador() {
    }

    public Paseador(int id, String apellido, String nombres, String horario) {
        this.id = id;
        this.apellido = apellido;
        this.nombres = nombres;
        this.horario = horario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
