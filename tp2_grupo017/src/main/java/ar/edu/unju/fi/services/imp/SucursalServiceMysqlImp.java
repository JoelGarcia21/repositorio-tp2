package ar.edu.unju.fi.services.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.repository.ISucursalRepository;
import ar.edu.unju.fi.services.ISucursalService;

@Service("sucursalServiceMysql")
public class SucursalServiceMysqlImp implements ISucursalService {

    @Autowired
    private Sucursal sucursal;

    @Autowired
    private ISucursalRepository sucursalRepository;

    
    /**
     * Método que retorna una lista con las sucursales cuyo estado es true.
     */
    @Override
    public List<Sucursal> getLista() {
        return this.sucursalRepository.findByEstado(true);
    }

    /**
     * Método que guarda en la base de datos una sucursal.
     */
    @Override
    public void guardar(Sucursal sucursal) {
        this.sucursalRepository.save(sucursal);
    }

    /**
     * Método que busca una sucursal según el codigo de la misma.
     */
    @Override
    public Sucursal getBy(Long codigo) {
        return this.sucursalRepository.findById(codigo).get();
    }

    /**
     * Método que realiza la actualización de una sucursal.
     */
    @Override
    public void modificar(Sucursal sucursal) {
        this.sucursalRepository.save(sucursal);
    }

    /**
     * Método que realiza el cambio de estado de una sucursal a false.
     */
    @Override
    public void eliminar(Long codigo) {
        Sucursal sucursalBuscada = getBy(codigo);
        sucursalBuscada.setEstado(false);
        this.sucursalRepository.save(sucursalBuscada);
    }

    /**
     * Método que retorna una sucursal.
     */
    @Override
    public Sucursal getSucursal() {
        return this.sucursal;
    }

    /**
     * Método que realiza la busqueda de sucursales según un rango de fechas.
     */
    @Override
    public List<Sucursal> getSucursalBeetwen(LocalDate inicio, LocalDate fin) {
        return this.sucursalRepository.findBetweenfechas(inicio, fin);
    }
    
}
