package ar.edu.unju.fi.services;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.fi.entity.Sucursal;

public interface ISucursalService {
	List<Sucursal> getLista();
	
	void guardar(Sucursal sucursal);
	
	Sucursal getBy(Long codigo);

	void modificar(Sucursal sucursal);
	
	void eliminar(Long codigo);
	
	Sucursal getSucursal();

	List<Sucursal> getSucursalBeetwen(LocalDate inicio, LocalDate fin);
}
