package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Servicio;
import ar.edu.unju.fi.repository.IServicioRepository;
import ar.edu.unju.fi.services.IServicioService;

@Service("servicioServiceMysql")
public class ServicioServiceMysql implements IServicioService {

    @Autowired
    private Servicio servicio;

    @Autowired
    private IServicioRepository servicioRepository;

    @Override
    public List<Servicio> getListado() {
        return (List<Servicio>) this.servicioRepository.findAll();
    }

    @Override
    public List<Servicio> getServicioByEstado(boolean estado) {
        return this.servicioRepository.findByEstado(estado);
    }

    @Override
    public Servicio getServicio() {
        return this.servicio;
    }

    @Override
    public Servicio getServicioById(Long id) {
        return this.servicioRepository.findById(id).get();
    }

    @Override
    public void guardarServicio(Servicio servicio) {
        this.servicioRepository.save(servicio);
    }

    @Override
    public void modificarServicio(Servicio servicioModificado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarServicio'");
    }

    @Override
    public void eliminarServicio(Long id) {
        Servicio servicioBuscado = getServicioById(id);
        servicioBuscado.setEstado(false);
        this.servicioRepository.save(servicioBuscado);
    }

    @Override
    public List<Servicio> getServiciosByDia(String dia) {
        return this.servicioRepository.findByDiaAndEstado(dia, true);
    }

}
