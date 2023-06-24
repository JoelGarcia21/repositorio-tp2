package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ser_id")
    private Long id;

    @Column(name = "ser_nombre")
    @Size(min=3, max = 20, message = "El nombre del servicio tiene que tener entre 3 y 20 caracteres")
    @NotBlank(message = "No puede estar en blanco")
    private String nombre;
    
    @Size(min = 3, max = 100, message = "El apellido tiene que tener entre 3 y 100 caractéres")
    @NotEmpty(message = "El apellido no puede ser vacío")
    private String apellido;
    
    @Size(min = 3, max = 100, message = "El nombre tiene que tener entre 3 y 100 caractéres")
    @NotEmpty(message = "El nombre no puede ser vacío")
    private String nombres;
    
    @Column(name = "ser_dia")
    @NotEmpty(message = "Debe ingresar un día")
    private String dia;

    @Column(name = "ser_horario")
    @NotEmpty(message = "Tiene que ingresar un horario")
    private String horario;


    public Servicio() {
    }


    public Servicio(Long id, String nombre, String apellido, String nombres, String dia, String horario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombres = nombres;
        this.dia = dia;
        this.horario = horario;
    }
    


    public String getDia() {
        return this.dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
