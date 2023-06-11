package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.listados.ListaConsejos;
import ar.edu.unju.fi.models.Consejos;
import ar.edu.unju.fi.services.IConsejoService;

@Service
public class ConsejoServiceImp implements IConsejoService{

	@Autowired
	private ListaConsejos listaConsejos;

    public ConsejoServiceImp() {
        listaConsejos = new ListaConsejos();
    }
    @Override
    public List<Consejos> listarConsejos() {
        return listaConsejos.getConsejos();
    }

    @Override
    public void guardarConsejo(Consejos consejo) {
        listaConsejos.agregarConsejo(consejo);
    }

    @Override
    public void modificarConsejo(int indice, Consejos nuevoConsejo) {
        listaConsejos.editarConsejo(indice, nuevoConsejo);
    }

    @Override
    public void eliminarConsejo(int indice) {
        listaConsejos.eliminarConsejo(indice);
    }
}
