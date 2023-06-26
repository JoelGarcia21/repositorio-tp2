package ar.edu.unju.fi.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Component
@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ser_id")
    private Long id;

    @Column(name = "ser_nombre")
    @NotBlank(message = "Ingresar el nombre del servicio")
    private String nombre;
    
        
    @Column(name = "ser_dia")
    @NotBlank(message = "Ingresar el día")    
    private String dia;

    @Column(name = "ser_horario")
    @NotBlank(message = "Ingresar el horario")
    private String horario;

    // @Column(name = "ser_apellido_emp")
    // @NotBlank(message = "Ingresar el apellido del empleado")
    // private String apellido;

    // @Column(name = "ser_nombre_emp")
    // @NotBlank(message = "Ingresar el nombre del empleado")
    // private String nombreEmpleado;

    @Column(name = "ser_estado")
    private boolean estado;

    @Autowired
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull(message = "Debe ingresar un empleado")
    private Empleado empleado;


    public Servicio() {
    }



    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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


    // public String getApellido() {
    //     return this.apellido;
    // }

    // public void setApellido(String apellido) {
    //     this.apellido = apellido;
    // }

    // public String getNombreEmpleado() {
    //     return this.nombreEmpleado;
    // }

    // public void setNombreEmpleado(String nombreEmpleado) {
    //     this.nombreEmpleado = nombreEmpleado;
    // }
    

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
