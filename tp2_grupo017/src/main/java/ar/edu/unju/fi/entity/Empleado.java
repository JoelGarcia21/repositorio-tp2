package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Component
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long codigo;
    
    @NotEmpty(message = "Debe ingresar el apellido")
    @Column(name = "emp_apellido", length = 20, nullable = false)
    private String apellido;
    
    @NotEmpty(message = "Debe ingresar el nombre")
    @Column(name = "emp_nombre", length = 20, nullable = false)
    private String nombre;
    
    
    @Min(message = "el año debe ser un valor mayor que 0", value = 0)
    @Column(name = "emp_anio_ingreso", nullable = false)
    private int anioIngreso;
    
    @Column(name = "emp_estado")
    private boolean estado;
    
    @Column(name = "emp_asignado")
    private boolean asignado;
    

    public Empleado() {
    }

    public Empleado(Long codigo, String apellido, String nombre, int anioIngreso, boolean estado, boolean asignado) {
        this.codigo = codigo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.anioIngreso = anioIngreso;
        this.estado = estado;
        this.asignado = asignado;
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioIngreso() {
        return this.anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
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

    public boolean isAsignado() {
        return this.asignado;
    }

    public boolean getAsignado() {
        return this.asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

}
