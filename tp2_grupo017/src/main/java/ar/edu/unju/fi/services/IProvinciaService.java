package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.entity.Provincia;

public interface IProvinciaService {
    
    public Provincia getProvincia();
    public List<Provincia> getProvincias();
    public List<Provincia> getByEstado(boolean estado);
    public Provincia getByCodigo(Long codigo);
    public void guardarProvincia(Provincia provincia);
    public void eliminarProvinciaByCodigo(Long codigo);
}
