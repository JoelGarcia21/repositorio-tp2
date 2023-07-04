package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Component
@Entity
@Table(name="Consejos") //Nombre de la tabla
public class Consejo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Genera de forma secuencial y automatica para la clave primaria.
	@Column(name="csj_id") //nombre de la columna para el ID.
	private Long id;
	
	@Column(name="consejo", nullable = false) // nullable = false, No acepta valores nulos.
	@NotEmpty(message = "Debe ingresar un consejo.")
	private String descripcion;

	@Column(name="csj_estado")
	private boolean estado; //El atribo indica si el objeto esta activo o no.
	
    public Consejo() {
    }


	public Consejo(Long id, String consejo, boolean estado) {
		this.id = id;
		this.descripcion = consejo;
		this.estado = estado;
	}


	public Consejo(String consejo) {
		this.descripcion = consejo;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String consejo) {
		this.descripcion = consejo;
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
