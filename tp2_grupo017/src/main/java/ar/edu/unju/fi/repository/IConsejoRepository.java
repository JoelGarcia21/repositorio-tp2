package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Consejo;

@Repository
public interface IConsejoRepository extends CrudRepository<Consejo, Long>{

	/**
     * Busca y devuelve una lista de consejos en función del estado.
     *
     * @param estado El estado de los consejos a buscar.
     * @return Una lista de consejos que cumplen con el estado especificado.
     */
	public List<Consejo> findByEstado(boolean estado);
	
	
}
