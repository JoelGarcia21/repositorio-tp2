package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Empleado;
import ar.edu.unju.fi.repository.IEmpleadoRepository;
import ar.edu.unju.fi.services.IEmpleadoService;

@Service("empleadoServiceMysql")
public class EmpleadoServiceMysql implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private Empleado empleado;

    @Override
    public Empleado getEmpleado() {
        return this.empleado;
    }

    @Override
    public List<Empleado> getEmplados() {
        return (List<Empleado>)empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoByCodigo(Long codigo) {
        return empleadoRepository.findById(codigo).get();
    }

    @Override
    public void guardarEmpleado(Empleado empleado) {
        this.empleadoRepository.save(empleado);
    }

    @Override
    public void eliminarEmpleadoByCodigo(Long codigo) {
        Empleado empleadoEncontrado = this.empleadoRepository.findById(codigo).get();
        empleadoEncontrado.setEstado(false);
        this.empleadoRepository.save(empleadoEncontrado);
    }

    @Override
    public List<Empleado> getEmpleadoByEstadoAndAsignado(boolean estado, boolean asignado) {
        return this.empleadoRepository.findByEstadoAndAsignado(estado, asignado);
    }

    @Override
    public List<Empleado> getEmpleadosByEstado(boolean estado) {
        return this.empleadoRepository.findByEstado(true);
    }

    @Override
    public List<Empleado> getEmpleadosByAsignado(boolean asignado) {
        return this.empleadoRepository.findByAsignado(asignado);
    }
    
}
