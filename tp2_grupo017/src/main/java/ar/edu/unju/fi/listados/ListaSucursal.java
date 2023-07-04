package ar.edu.unju.fi.listados;
 
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.*;

// import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class ListaSucursal {
	private List<Sucursal> sucursales;

	
	public ListaSucursal() {
		sucursales = new ArrayList<Sucursal>();
		// sucursales.add(new Sucursal("Pata de Oro - Centro", "Alvear 1609", "S.S. de Jujuy", LocalDate.of(2020, 2, 12), "gold_alvear@gmail.com", "0388155555555"));
		// sucursales.add(new Sucursal("Pata de Oro - Perico", "Chaile 99", "Perico", LocalDate.of(2020, 6, 21), "gold_chaile@gmail.com", "0388151111111"));
		// sucursales.add(new Sucursal("Pata de Oro - Libertador", "Cofre 543", "Libertador", LocalDate.of(2021, 1, 31), "gold_lib@gmail.com", "0388152222222"));
	}
	
	public List<Sucursal> getSucursales(){
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
}
//

