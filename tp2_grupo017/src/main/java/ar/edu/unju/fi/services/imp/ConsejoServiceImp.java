package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.listados.ListaConsejos;
import ar.edu.unju.fi.services.IConsejoService;
/**
 * Implementación de la interfaz IConsejoService que proporciona los métodos para administrar los consejos.
 */

@Service("alumnoServiceImp")
public class ConsejoServiceImp implements IConsejoService{

	@Autowired
	private ListaConsejos listaConsejos;

   /* public ConsejoServiceImp() {
        listaConsejos = new ListaConsejos();
    }*/
    @Override
    public List<Consejo> listarConsejos() {
        return listaConsejos.getConsejos();
    }

    @Override
    public void guardarConsejo(Consejo consejo) {
        listaConsejos.agregarConsejo(consejo);
    }

    @Override
    public void modificarConsejo(Consejo nuevoConsejo) {
        // listaConsejos.editarConsejo(indice, nuevoConsejo);
    }

    @Override
    public void eliminarConsejo(Long indice) {
        // listaConsejos.eliminarConsejo(indice);
    }

    @Override
    public Consejo getByIdConsejo(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByIdConsejo'");
    }

    @Override
    public Consejo getConsejo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConsejo'");
    }
}
