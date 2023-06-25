package ar.edu.unju.fi.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;



@Component
@Entity
@Table(name = "productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_codigo")
	private Long codigo;
	
	@NotEmpty(message="obligatorio ingresar nombre")
	@Size(min=4,max=20 , message="el nombre tiene que tener minimo 4 y maximo de 20 caracteres")
	@Column(name = "prod_nombre", length = 20)
	private String nombre;
	
	@Positive(message="el valor ingresado tiene que ser positivo")
	@Column(name = "prod_precio")
	private float precio;
	
	
	@Autowired
	@ManyToOne
	@JoinColumn(name = "prod_categoria")
	@NotNull(message = "Debe ingresar una categoría")	
    private Categoria categoria;
	
    @Min(value=0, message="valor minimo es 0")
    @Max(value=50, message="el valor no puede ser mas de 50")
	@Column(name = "prod_descuento")
	private int descuento;

	@Column(name = "prod_imagen")
	private String imagen;

	@Column(name = "prod_estado")
	private boolean estado;
	
	
	

	public Producto() {
		super();
	}




	public Producto(Long codigo, String nombre, float precio, Categoria categoria, int descuento, String imagen, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.descuento = descuento;
		this.imagen = imagen;
		this.estado = estado;
	}
	


	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}


	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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




	@Override
	public String toString() {
		return "{" +
			" codigo='" + getCodigo() + "'" +
			", nombre='" + getNombre() + "'" +
			", precio='" + getPrecio() + "'" +
			", categoria='" + getCategoria() + "'" +
			", descuento='" + getDescuento() + "'" +
			", imagen='" + getImagen() + "'" +
			", estado='" + isEstado() + "'" +
			"}";
	}
	
	
	
	/**
	 * el metodo calcularDescuento :
	 * calcula el descuento y se lo resta al precio.
	 * @return float
	 */
	public float calcularDescuento  () {
		float descuentos;
		descuentos = this.descuento*this.precio /100 ;
		
		return (this.precio -descuentos);
	}
}
