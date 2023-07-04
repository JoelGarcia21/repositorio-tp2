package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Component
@Entity
@Table(name = "provincias")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prov_codigo")
    private Long codigo;

    @Column(name = "prov_nombre")
    @NotEmpty(message = "Debe ingresar el nombre de una provincia")
    private String nombre;

    @OneToMany(mappedBy = "provincia")
    private List<Sucursal> sucursales;

    @Column(name = "prov_estado")
    private boolean estado;

    public Provincia() {
    }

    public Provincia(Long codigo, String nombre, List<Sucursal> sucursales, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sucursales = sucursales;
        this.estado = estado;
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Sucursal> getSucursales() {
        return this.sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
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
