package ar.edu.unju.fi.models;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;



@Component
public class Producto {
	@NotEmpty(message="obligatorio ingresar nombre")
	@Size(min=4,max=20 , message="el nombre tiene que tener minimo 4 y maximo de 20 caracteres")
	private String nombre;
	
	// @Min(value=4, message="como minimo es de 4 caracteres ")
	private int codigo;
	
	@Positive(message="el valor ingresado tiene que ser positivo")
	private float precio;
	
	@NotEmpty(message="obligatorio ingresar categoria")
    private String categoria;
	
    @Min(value=0, message="valor minimo es 0")
    @Max(value=50, message="el valor no puede ser mas de 50")
	private int descuento;

	private String imagen;
	
	
	

	public Producto() {
		super();
	}





	public Producto(String nombre, int codigo, float precio, String categoria, int descuento, String imagen) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.categoria = categoria;
		this.descuento = descuento;
		this.imagen = imagen;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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



	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", codigo=" + codigo + ", precio=" + precio + ", categoria=" + categoria
				+ ", descuento=" + descuento + "]";
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
