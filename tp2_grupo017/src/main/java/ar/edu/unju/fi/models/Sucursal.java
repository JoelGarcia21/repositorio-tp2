package ar.edu.unju.fi.models;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Component
public class Sucursal {
		@NotEmpty(message="el nombre no puede estar vacio") 
		private String nombre;
		@Size(min=5, max=100, message="la direccion debe contener entre 5 y 100 caracteres")
		private String direccion;
		@NotBlank(message="Debe seleccionar una provincia")
		private String provincia;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@NotNull(message="La fecha no puede ser null")
		@Past(message="La fecha no puede ser posterior a la actual")
		private LocalDate fechaInicio;
		@Email(message="Debe ingresar un email con formato valido")
		@NotEmpty(message="El mail no puede ser vacio")
		private String email;
		@NotEmpty(message="El telefono no puede ser vacio")
		private String telefono;
		
		public Sucursal() {
			
		}
		
		
		
		public Sucursal(String nombre, String direccion, String provincia, LocalDate fecha, String email,
				String telefono) {
			super();
			this.nombre = nombre;
			this.direccion = direccion;
			this.provincia = provincia;
			this.fechaInicio = fecha;
			this.email = email;
			this.telefono = telefono;
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
		public String getProvincia() {
			return provincia;
		}
		public void setProvincia(String provincia) {
			this.provincia = provincia;
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
