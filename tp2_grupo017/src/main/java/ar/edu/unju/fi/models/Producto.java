package ar.edu.unju.fi.models;

public class Producto {
	private String nombre;
	private int codigo;
	private float precio;
    private String categoria;
	private int descuento;
	
	
	
	
	public Producto() {
		super();
	}




	public Producto(String nombre, int codigo, float precio, String categoria, int descuento) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.categoria = categoria;
		this.descuento = descuento;
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




	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", codigo=" + codigo + ", precio=" + precio + ", categoria=" + categoria
				+ ", descuento=" + descuento + "]";
	}
	
	
	public float calcularDescuento  () {
		float descuentos;
		descuentos = this.descuento*this.precio /100 ;
		
		return (this.precio -descuentos);
	}
}
