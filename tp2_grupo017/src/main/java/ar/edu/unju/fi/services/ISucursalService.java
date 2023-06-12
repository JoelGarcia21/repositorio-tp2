package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.models.Sucursal;
import jakarta.validation.Valid;

public interface ISucursalService {
	List<Sucursal> getLista();
	
	void guardar(@Valid Sucursal sucursal);
	
	Sucursal getBy(String nombre);

	void modificar(Sucursal sucursal);
	
	void eliminar(Sucursal sucursalEncontrada);
	
	Sucursal getSucursal();
}
