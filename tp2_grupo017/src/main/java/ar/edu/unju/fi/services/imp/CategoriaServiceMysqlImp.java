package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.repository.ICategoriaRepository;
import ar.edu.unju.fi.services.ICategoriaService;

@Service("categoriaServiceMysql")
public class CategoriaServiceMysqlImp implements ICategoriaService {

    @Autowired
    private Categoria categoria;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getCategorias() {
        return (List<Categoria>)categoriaRepository.findByEstado(true);
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public Categoria getCateriaById(Long id) {
        return categoriaRepository.findById(id).get();
    }

    @Override
    public Categoria getCategoria() {
        return this.categoria;
    }

    @Override
    public void eliminarCategoriaById(Long id) {
        Categoria categoriaEncontrado = categoriaRepository.findById(id).get();
        categoriaEncontrado.setEstado(false);
        categoriaRepository.save(categoriaEncontrado);
    }
    
}
