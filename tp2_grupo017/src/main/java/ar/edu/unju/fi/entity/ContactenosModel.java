package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactenosModel {
	@NotBlank(message = ".El nombre es obligatorio.")
	private String nombre;
	@NotBlank(message = ".El correo es obligatorio")
    @Email(message = ".El correo electrónico no es válido")
	private String correo;
	@NotBlank(message = ".Necesitamos saber su ciudad.")
	private String ciudad;
	@NotBlank(message = ".El campo de mensaje no puede estar vacio.")
	private String mensaje;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
