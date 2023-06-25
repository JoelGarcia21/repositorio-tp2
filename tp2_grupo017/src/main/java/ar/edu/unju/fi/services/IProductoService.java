package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.entity.Producto;

public interface IProductoService {
    
    List<Producto> getProductos();
    void guardarProducto(Producto unProducto);
    void modificarProducto(int posicion, Producto unProducto);
    Producto buscarProductoByNombre(String nombre);
    Producto buscarProductoByCodigo(Long codigo);
    void eliminarProductoByNombre(String nombre);
    void eliminarProductoByCodigo(Long codigo);

    Producto getProducto();
}
