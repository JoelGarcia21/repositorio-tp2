package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.entity.Empleado;

public interface IEmpleadoService {
    Empleado getEmpleado();
    List<Empleado> getEmplados();
    Empleado getEmpleadoByCodigo(Long codigo);
    void guardarEmpleado(Empleado empleado);
    void eliminarEmpleadoByCodigo(Long codigo);

    List<Empleado> getEmpleadoByEstadoAndAsignado(boolean estado, boolean asignado);
    List<Empleado> getEmpleadosByEstado(boolean estado);
    List<Empleado> getEmpleadosByAsignado(boolean asignado);
}
