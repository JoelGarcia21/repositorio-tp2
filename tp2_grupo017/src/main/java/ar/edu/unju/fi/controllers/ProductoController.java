package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.listados.ListaProductos;
import ar.edu.unju.fi.models.Producto;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
ListaProductos manejaListas = new ListaProductos(); 
	
	@GetMapping("/listado")  
	public String producto(Model model) {  
	
		model.addAttribute( "lista",  manejaListas.getLista());
		return "creacion_de_productos";
	}		
	
	
	@GetMapping("/nuevo")  
	public String formularioIngreso(Model model) {  
		Producto producto = new  Producto();   
		model.addAttribute("producto",  producto);
		model.addAttribute("edicion",  false);
		return "nuevo_producto";//formulario
	}
	
	
	
	@PostMapping("/guardar")
	public String guardarProducto(Model model , Producto producto) {  
		
		if (producto.getNombre() == "" || producto.getCodigo() == 0 || producto.getPrecio() == 0 ||  producto.getCategoria() =="" || producto.getDescuento() == 0 ) {
			model.addAttribute("error", "es obligatorio llenar todos los campos");
			return "nuevo_producto";
		}
		manejaListas.agregarProducro(producto);
		//System.out.println(manejaListas.getLista().size());
		
		for (Producto prot : manejaListas.getLista()) {
			//System.out.println(prot.toString());
		}
		
		//for (int i=0; i<=manejaListas.getLista().size()-1;i++) {
			//manejaListas.getLista().get(i);
			//System.out.println(manejaListas.getLista().get(i).toString());
		//}
		model.addAttribute( "lista",  manejaListas.getLista());
		return "redirect:/producto/listado";
		 
				
	
	} 
	
	@GetMapping("/editar/{nombre}")
	public String modificar(Model model , @PathVariable(value="nombre") String nombre ) {
		Producto p =manejaListas.buscarProductoporNombre(nombre);
		model.addAttribute("producto",p );
		// en q lugar de la lista se encuentra el objeto a editar
		int indice = manejaListas.getLista().indexOf(p);
		model.addAttribute("edicion",  true);
		model.addAttribute("indice",  indice);
		return "nuevo_producto";
	}
	
	
	@PostMapping("/editar/{indice}")
	public String modificarProducto(@ModelAttribute("producto") Producto producto,@PathVariable(value="indice") int indice ) {
		
		System.out.println(indice);	
		manejaListas.getLista().set(indice, producto);
		return "redirect:/producto/listado";
	}
	
	@GetMapping("/eliminar/{nombre}")
	public String eliminarProducto(@PathVariable(value="nombre") String nombre ) {
		Producto p = manejaListas.buscarProductoporNombre(nombre);
		manejaListas.getLista().remove(p);
		return "redirect:/producto/listado";
	}
	
}
