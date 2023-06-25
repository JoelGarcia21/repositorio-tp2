package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Producto;
import ar.edu.unju.fi.repository.IProductoRepository;
import ar.edu.unju.fi.services.IProductoService;

@Service("productoServiceMysql")
public class ProductoServiceMsqlImp implements IProductoService {
    
    @Autowired
    private Producto producto;

    @Autowired
    private IProductoRepository productoRepository;

    /**
     * Método que retorna una lista con los productos en estado activo
     * 
     * @return una list<Producto>
     */
    @Override
    public List<Producto> getProductos() {
        return this.productoRepository.findByEstado(true);
    }

    /**
     * Método que guarda un producto en la base de datos
     * 
     * @param unProducto un objeto de la clase Producto
     */
    @Override
    public void guardarProducto(Producto unProducto) {
        
        this.productoRepository.save(unProducto);
    }

    
    /**
     * Método que realiza la modificación de un producto
     * 
     * @param posicion del producto en la lista (caso de que se use una lista)
     * @param unProducto objeto de la clase Producto, que es el objeto a modificar.
     */
    @Override
    public void modificarProducto(int posicion, Producto unProducto) {
        this.productoRepository.save(unProducto);
    }

    /**
     * Método que busca un producto en la base de dato según el campo nombre.
     * este método no esta implementado en forma completa.
     * @param nombre parametro del tipo String que representa el nombre del producto a buscar.
     * @return null
     */
    @Override
    public Producto buscarProductoByNombre(String nombre) {
        // para este caso no necesita este método
        return null;
    }

    /**
     * Método que busca un producto en la base de datos según su código.
     * 
     * @param codigo parametro del tipo Long que representa el codigo del producto a buscar.
     * @return Un objeto del tipo Producto.
     */
    @Override
    public Producto buscarProductoByCodigo(Long codigo) {
        return this.productoRepository.findById(codigo).get();
    }

    @Override
    public void eliminarProductoByNombre(String nombre) {
        // no se usa en esta implementacion
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProductoByNombre'");
    }

    /**
     * Método que busca un producto según su codigo y cambia el estado a false.
     * 
     * @param codigo parametro de tipo Long que representa el codigo del producto a buscar.
     */
    @Override
    public void eliminarProductoByCodigo(Long codigo) {
        Producto productoEncontrado = buscarProductoByCodigo(codigo);
        productoEncontrado.setEstado(false);
        productoRepository.save(productoEncontrado);
    }

    
    /**
     * Método que retorna un objeto de tipo Producto
     * 
     * @return un objeto del tipo Producto.
     */
    @Override
    public Producto getProducto() {
        return this.producto;
    }


    
}
