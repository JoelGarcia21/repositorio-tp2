package ar.edu.unju.fi.listados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.models.Producto;

@Component
public class ListaProductos {
	
	private List<Producto> lista;
	
	public ListaProductos() {
		super();
		lista = new ArrayList<Producto>();
	}

	public List<Producto> getLista() {
		return lista;
	}

	public void setLista(List<Producto> lista) {
		this.lista = lista;
	}

	public void agregarProducro(Producto producto) {  
		lista.add(producto);                          

	}
	/** el metodo buscaProductoporNombre 
	 * busca un producto por su nombre.
	 * @param nombre
	 * @return Producto
	 * 
	 */
	public Producto buscarProductoporNombre(String nombre) {
		Producto productoEncontrado = new Producto();
		for(Producto buscar : lista) {
			if(buscar.getNombre().equals(nombre) ) {
				productoEncontrado = buscar;
			}
		}
		System.out.println(productoEncontrado);
		return productoEncontrado;
	}
	
	
	
}
