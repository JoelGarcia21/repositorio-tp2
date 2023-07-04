package ar.edu.unju.fi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Sucursal;

@Repository
public interface ISucursalRepository extends CrudRepository<Sucursal, Long> {

    /**
     * Método que retorna una lista de sucursales,la cuaal está filtrada por estado.
     * @param estado tipo boolean
     * @return una lista objetos de tipo sucursal.
     */
    List<Sucursal> findByEstado(boolean estado);

    /**
     * Método que retorna una lista de sucursales, dentro de un rango de fechas determiando.
     * @param fecha1 parametro de tipo LocalDate que indica la fecha desde donde realizará la busqueda.
     * @param fecha2 parametro de tipo LocalDate que indica la fecha limite de busqueda.
     * @return un listado de sucursales.
     */
    @Query("SELECT s FROM Sucursal s WHERE s.fechaInicio BETWEEN ?1 AND ?2")
    List<Sucursal> findBetweenfechas(LocalDate fecha1, LocalDate fecha2);
    
}
