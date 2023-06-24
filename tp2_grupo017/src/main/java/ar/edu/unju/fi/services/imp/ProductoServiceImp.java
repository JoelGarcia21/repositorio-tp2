package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Producto;
import ar.edu.unju.fi.listados.ListaProductos;
import ar.edu.unju.fi.services.IProductoService;

@Service
public class ProductoServiceImp implements IProductoService {
    @Autowired
    private Producto producto;

    @Autowired
    private ListaProductos listaProductos;

    /**
     * Retorna la lista de Productos.
     * 
     * @return List<Producto>
     */
    @Override
    public List<Producto> getProductos() {
        return listaProductos.getLista();
    }

    /**
     * Método que guarda un objeto de tipo producto en la lista de productos.
     * 
     * @param unProducto
     */
    @Override
    public void guardarProducto(Producto unProducto) {
        // if (this.listaProductos.getLista().isEmpty()) {
        //     // lista vacía
        //     unProducto.setCodigo(1l);
        // } else {
        //     // lista tiene productos, buscar el ultimo codigo ingresado e incrementar en
        //     // uno.
        //     int ultimoCodigo = listaProductos.getLista().get(listaProductos.getLista().size() - 1).getCodigo();
        //     unProducto.setCodigo(ultimoCodigo + 1);
        // }
        this.listaProductos.getLista().add(unProducto);
    }

    
    /** 
     * Método que modifica un producto según su posicion en la lista.
     * @param posicion tipo int
     * @param unProducto tipo Producto
     */
    @Override
    public void modificarProducto(int posicion, Producto unProducto) {
        listaProductos.getLista().set(posicion, unProducto);

    }

    /**
     * Método que busca un Producto según el nombre del mismo y devuelve el
     * producto.
     * 
     * @param nombre tipo String.
     * @return Producto
     */
    @Override
    public Producto buscarProductoByNombre(String nombre) {
        Producto productoEncontrado = null;
        for (Producto prod : this.listaProductos.getLista()) {
            if (prod.getNombre().equals(nombre)) {
                productoEncontrado = prod;
                break;
            }
        }
        return productoEncontrado;
    }

    /**
     * Método que busca un producto según el código del mismo.
     * 
     * @param codigo tipo Long.
     * @return Producto
     */
    @Override
    public Producto buscarProductoByCodigo(Long codigo) {
        Producto productoEncontrado = null;
        for (Producto prod : this.listaProductos.getLista()) {
            if (prod.getCodigo() == codigo) {
                productoEncontrado = prod;
                break;
            }
        }
        return productoEncontrado;
    }

    /**
     * Método que elimina un producto de la lista según nombre del mismo.
     * 
     * @param nombre tipo String
     */
    @Override
    public void eliminarProductoByNombre(String nombre) {
        Producto productoEncontrado = buscarProductoByNombre(nombre);
        this.listaProductos.getLista().remove(productoEncontrado);
    }

    /**
     * Método que elimina un producto de la lista según el código del mismo.
     * 
     * @param codigo tipo Long.
     */
    @Override
    public void eliminarProductoByCodigo(Long codigo) {
        Producto productoEncontrado = buscarProductoByCodigo(codigo);
        this.listaProductos.getLista().remove(productoEncontrado);
    }

    /**
     * Método que retorna un objeto del tipo Producto.
     * 
     * @return Producto
     */
    @Override
    public Producto getProducto() {
        return this.producto;
    }

}
