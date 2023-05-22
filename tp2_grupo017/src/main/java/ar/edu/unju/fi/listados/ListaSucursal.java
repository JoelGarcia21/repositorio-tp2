package ar.edu.unju.fi.listados;
 
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unju.fi.models.*;

public class ListaSucursal {
	private List<Sucursal> sucursales;
	
	
	public ListaSucursal() {
		sucursales = new ArrayList<Sucursal>();
		sucursales.add(new Sucursal("amigos", "Alvear", "Jujuy", LocalDate.of(2020, 2, 12), "facu_uama", "0388"));
	}
	
	public List<Sucursal> getSucursales(){
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
}


