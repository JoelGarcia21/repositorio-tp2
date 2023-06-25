package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.repository.IConsejoRepository;
import ar.edu.unju.fi.services.IConsejoService;

@Service("consejoServiceMysqulImp") // Como hay dos implementaciones para la misma interfaz, nombramos la implementacion.
@Primary
public class ConsejoServiceMysqlImp implements IConsejoService {

	@Autowired
	private IConsejoRepository consejoRepository;
	
	/**
	 * Devuelve una lista de consejos activos.
	 *
	 * @return Lista de consejos activos.
	 */
	@Override
	public List<Consejo> listarConsejos() {
		return consejoRepository.findByEstado(true);
	}

	/**
	 * Guarda un nuevo consejo estableciendo su estado como activo.
	 *
	 * @param consejo El consejo a guardar.
	 */
	@Override
	public void guardarConsejo(Consejo consejo) {
		consejo.setEstado(true);
		consejoRepository.save(consejo);

	}

	/**
	 * Modifica un consejo existente en el repositorio.
	 *
	 * @param indice      El índice del consejo a modificar.
	 * @param nuevoConsejo El nuevo objeto Consejo con los datos actualizados.
	 */
	@Override
	public void modificarConsejo(int indice, Consejo nuevoConsejo) {
		consejoRepository.save(nuevoConsejo);
	}

	/**
	 * Elimina un consejo estableciendo su estado como inactivo.
	 *
	 * @param indice El índice del consejo a eliminar.
	 */
	@Override
	public void eliminarConsejo(int indice) {
		
		List<Consejo> consejos = listarConsejos();
	    if (indice >= 0 && indice < consejos.size()) {
	        Consejo consejo = consejos.get(indice);
	        consejo.setEstado(false);
	        consejoRepository.save(consejo);
	    }
		
	}

}
