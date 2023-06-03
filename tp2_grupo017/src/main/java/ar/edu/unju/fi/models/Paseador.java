package ar.edu.unju.fi.models;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
public class Paseador {
    private int id;
    @Size(min = 3, max = 100, message = "El apellido tiene que tener entre 3 y 100 caractéres")
    @NotEmpty(message = "El apellido no puede ser vacío")
    private String apellido;
    @Size(min = 3, max = 100, message = "El nombre tiene que tener entre 3 y 100 caractéres")
    @NotEmpty(message = "El nombre no puede ser vacío")
    private String nombres;
    @NotEmpty(message = "Tiene que ingresar un horario")
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
