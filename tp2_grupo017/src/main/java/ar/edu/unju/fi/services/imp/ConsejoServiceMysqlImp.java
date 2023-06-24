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
	
	//@Autowired
	//private Consejo consejo;
	
	@Override
	public List<Consejo> listarConsejos() {
		return consejoRepository.findByEstado(true); //Muestra solo consejos activos o con el valor verdadero.
	}

	@Override
	public void guardarConsejo(Consejo consejo) {
		consejo.setEstado(true);
		consejoRepository.save(consejo);

	}

	@Override
	public void modificarConsejo(int indice, Consejo nuevoConsejo) {
		consejoRepository.save(nuevoConsejo);
	}

	@Override
	public void eliminarConsejo(int indice) {
		
		List<Consejo> consejos = listarConsejos();
	    if (indice >= 0 && indice < consejos.size()) {
	        Consejo consejo = consejos.get(indice);
	        consejo.setEstado(false);
	        consejoRepository.save(consejo);
	    }
		// Recuperar el consejo por su índice utilizando el método findById del repositorio.
		// Realizamos una conversión explícita de int a long para asegurar la compatibilidad.
		// Si se encuentra el consejo, se asigna a la variable 'consejo'.
		// En caso de no encontrarse, se asigna null a la variable 'consejo'.
		// Esto nos permite verificar si se encontró un consejo válido antes de continuar con las operaciones adicionales.
	    //Consejo consejo = consejoRepository.findById((long) indice).orElse(null);
	    
		//Eliminarcion logica: No se borra la fila en la tabla, solo se cambia el valor de la columna estado a false.
		//consejo.setEstado(false);
		
		// Guardar los cambios en el repositorio
        //consejoRepository.save(consejo);
		
	}

}
