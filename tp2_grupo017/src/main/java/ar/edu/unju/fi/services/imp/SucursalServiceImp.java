package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.listados.ListaSucursal;
import ar.edu.unju.fi.models.Sucursal;
import ar.edu.unju.fi.services.ISucursalService;
import jakarta.validation.Valid;

@Service
public class SucursalServiceImp implements ISucursalService{
	@Autowired
	private ListaSucursal listaSucursales;
	@Autowired
	private Sucursal sucursal;
	
	public List<Sucursal> getLista() {
		return listaSucursales.getSucursales();
	}
	
	public void guardar(@Valid Sucursal sucursal) {
		listaSucursales.getSucursales().add(sucursal);
	}
	
	public Sucursal getBy(String nombre) {
		Sucursal sucursalEncontrada = null;
		for(Sucursal sucu: listaSucursales.getSucursales()) {
			if(sucu.getNombre().equals(nombre)) {
				sucursalEncontrada = sucu;
				break;
			}
		}
		return sucursalEncontrada;
	}
	
	public void modificar(Sucursal sucursal) {
		for(Sucursal sucu: listaSucursales.getSucursales()) {
			if(sucu.getNombre().equals(sucursal.getNombre())) {
				sucu.setDireccion(sucursal.getDireccion());
				sucu.setEmail(sucursal.getEmail());
				sucu.setFechaInicio(sucursal.getFechaInicio());
				sucu.setProvincia(sucursal.getProvincia());
				sucu.setTelefono(sucursal.getTelefono());
			}
		}
	}
	
	public void eliminar(Sucursal sucursal) {
		listaSucursales.getSucursales().remove(sucursal);
	}
	
	//@Override
	public Sucursal getSucursal() {
		return sucursal;
	}
}
