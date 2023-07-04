package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.Provincia;

public interface IProvinciaRepository extends CrudRepository<Provincia, Long>{

    public List<Provincia> findByEstado(boolean estado);
    
}
