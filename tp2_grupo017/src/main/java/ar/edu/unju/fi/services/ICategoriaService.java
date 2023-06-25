package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.entity.Categoria;

public interface ICategoriaService {
    

    public List<Categoria> getCategorias();
    public void guardarCategoria(Categoria categoria);
    public Categoria getCateriaById(Long id);
    public Categoria getCategoria();
    public void eliminarCategoriaById(Long id);
    
}
