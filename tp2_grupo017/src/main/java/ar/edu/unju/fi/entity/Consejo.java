package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name="Consejos") //Nombre de la tabla
public class Consejo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Genera de forma secuencial y automatica para la clave primaria.
	@Column(name="csj_id") //nombre de la columna para el ID.
	private Long id;
	
	@Column(name="consejo", nullable = false) // nullable = false, No acepta valores nulos.
	private String consejo;

	@Column(name="csj_estado")
	private boolean estado; //El atribo indica si el objeto esta activo o no.
	
    public Consejo() {
    }

    public Consejo(String consejo) {
        this.consejo = consejo;
    }

    public String getConsejo() {
        return consejo;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
		
}
