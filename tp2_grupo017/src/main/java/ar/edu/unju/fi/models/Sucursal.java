package ar.edu.unju.fi.models;

import java.time.LocalDate;

public class Sucursal {
		private String nombre;
		private String direccion;
		private String provincia;
		private LocalDate fecha;
		private String email;
		private String telefono;
		
		public Sucursal() {
			
		}
		
		
		
		public Sucursal(String nombre, String direccion, String provincia, LocalDate fecha, String email,
				String telefono) {
			super();
			this.nombre = nombre;
			this.direccion = direccion;
			this.provincia = provincia;
			this.fecha = fecha;
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
		public LocalDate getFecha() {
			return fecha;
		}
		public void setFecha(LocalDate fecha) {
			this.fecha = fecha;
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
