package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Provincia;
import ar.edu.unju.fi.repository.IProvinciaRepository;
import ar.edu.unju.fi.services.IProvinciaService;

@Service("provinciaServiceMysql")
public class ProvinciaServiceMysqlImp implements IProvinciaService {

    @Autowired
    private Provincia provincia;

    @Autowired
    private IProvinciaRepository provinciaRepository;

    @Override
    public Provincia getProvincia() {
        return this.provincia;
    }

    @Override
    public List<Provincia> getProvincias() {
        return (List<Provincia>) this.provinciaRepository.findAll();
    }

    @Override
    public List<Provincia> getByEstado(boolean estado) {
        return this.provinciaRepository.findByEstado(estado);
    }

    @Override
    public Provincia getByCodigo(Long codigo) {
        return this.provinciaRepository.findById(codigo).get();
    }

    @Override
    public void guardarProvincia(Provincia provincia) {
        this.provinciaRepository.save(provincia);
    }

    @Override
    public void eliminarProvinciaByCodigo(Long codigo) {
        Provincia provinciaBuscada = getByCodigo(codigo);
        provinciaBuscada.setEstado(false);
        this.provinciaRepository.save(provinciaBuscada);
    }

    
}
