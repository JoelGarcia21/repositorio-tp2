package ar.edu.unju.fi.services.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.listados.ListaSucursal;
import ar.edu.unju.fi.services.ISucursalService;

@Service("sucursalServiceImp")
public class SucursalServiceImp implements ISucursalService{
	
	@Autowired
	private ListaSucursal listaSucursales;
	@Autowired
	private Sucursal sucursal;
	
		
	@Override
	public Sucursal getSucursal() {
		return sucursal;
	}

	@Override
	public Sucursal getBy(Long codigo) {
		Sucursal sucursalEncontrada = null;
		for(Sucursal sucu: listaSucursales.getSucursales()) {
			if(sucu.getCodigo()==codigo) {
				sucursalEncontrada = sucu;
				break;
			}
		}
		return sucursalEncontrada;
	}

	@Override
	public void eliminar(Long codigo) {
		listaSucursales.getSucursales().remove(sucursal);
	}

	
	@Override
	public List<Sucursal> getLista() {
		return listaSucursales.getSucursales();
	}

	@Override
	public void guardar(Sucursal sucursal) {
		listaSucursales.getSucursales().add(sucursal);
	}

	@Override
	public void modificar(Sucursal sucursal) {
		for(Sucursal sucu: listaSucursales.getSucursales()) {
			if(sucu.getCodigo() == sucursal.getCodigo()) {
				sucu.setDireccion(sucursal.getDireccion());
				sucu.setEmail(sucursal.getEmail());
				sucu.setFechaInicio(sucursal.getFechaInicio());
				sucu.setProvincia(sucursal.getProvincia());
				sucu.setTelefono(sucursal.getTelefono());
			}
		}
	}

	@Override
	public List<Sucursal> getSucursalBeetwen(LocalDate inicio, LocalDate fin) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getSucursalBeetwen'");
	}
}
