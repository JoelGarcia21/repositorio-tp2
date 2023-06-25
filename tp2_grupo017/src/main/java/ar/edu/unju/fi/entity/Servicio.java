package ar.edu.unju.fi.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String nombre;
    
        
    @Column(name = "ser_dia")
    @NotNull(message = "debe ingresar un día")
    private String dia;

    @Column(name = "ser_horario")
    @NotEmpty(message = "Tiene que ingresar un horario")
    private String horario;

    @Autowired
    @OneToOne
    @JoinColumn(name = "emp_id")
    // @NotNull(message = "debe elegir un empleado")
    private Empleado empleado;

    @Column(name = "ser_estado")
    private boolean estado;


    public Servicio() {
    }




    public Servicio(Long id, String nombre, String dia, String horario, Empleado empleado, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.horario = horario;
        this.empleado = empleado;
        this.estado = estado;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDia() {
        return this.dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    

}
