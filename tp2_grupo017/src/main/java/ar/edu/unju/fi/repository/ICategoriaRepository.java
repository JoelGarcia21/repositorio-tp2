package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Categoria;
import java.util.List;


@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria, Long> {
    
    public List<Categoria> findByEstado(boolean estado);

}
