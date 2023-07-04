package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "sucursales")
public class Sucursal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "suc_codigo")
	private Long codigo;

	@NotEmpty(message = "el nombre no puede estar vacio")
	@Column(name = "suc_nombre")
	private String nombre;
	
	@Size(min = 5, max = 100, message = "la direccion debe contener entre 5 y 100 caracteres")
	@Column(name = "suc_direcion")
	private String direccion;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prov_codigo")	
	private Provincia provincia;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "La fecha no puede ser null")
	@Past(message = "La fecha no puede ser posterior a la actual")
	@Column(name = "suc_fecha_inicio")
	private LocalDate fechaInicio;
	
	@Email(message = "Debe ingresar un email con formato valido")
	@NotEmpty(message = "El mail no puede ser vacio")
	@Column(name = "suc_email")
	private String email;
	
	@NotEmpty(message = "El telefono no puede ser vacio")
	@Column(name = "suc_telefono")
	private String telefono;

	@Column(name = "suc_estado")
	private boolean estado;

	public Sucursal() {

	}


	public Sucursal(Long codigo, String nombre, String direccion, Provincia provincia, LocalDate fechaInicio, String email, String telefono, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.provincia = provincia;
		this.fechaInicio = fechaInicio;
		this.email = email;
		this.telefono = telefono;
		this.estado = estado;
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
	

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fecha) {
		this.fechaInicio = fecha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
//
