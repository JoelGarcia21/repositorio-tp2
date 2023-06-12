package ar.edu.unju.fi.controllers;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

// import ar.edu.unju.fi.listados.ListaProductos;
import ar.edu.unju.fi.models.Producto;
import ar.edu.unju.fi.services.IProductoService;
import ar.edu.unju.fi.services.IUploadFileService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	// @Autowired
	// private ListaProductos manejaListas; // = new ListaProductos();
	// agrega las variables del servicio.
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	private static final Log LOGGER = LogFactory.getLog(ProductoController.class);
	private String titulo;

	/**
	 * el metodo producto renderiza la pagina creacion_de_productos(listado de
	 * productos)
	 * 
	 * @param model
	 * @return String
	 */

	@GetMapping("/listado")
	public String producto(Model model) {
		this.titulo = "Productos";
		// model.addAttribute("lista", manejaListas.getLista());
		model.addAttribute("lista", productoService.getProductos());
		model.addAttribute("titulo", this.titulo);
		return "creacion_de_productos";
	}

	/**
	 * el metodo formularioIngreso renderiza la pagina nuevo_producto
	 * 
	 * @param model
	 * @return String
	 */

	@GetMapping("/nuevo")
	public String formularioIngreso(Model model) {
		// Producto producto = new Producto();

		this.titulo = "Nuevo producto";
		model.addAttribute("producto", productoService.getProducto());
		model.addAttribute("edicion", false);
		model.addAttribute("titulo", this.titulo);
		return "nuevo_producto";// formulario
	}

	/**
	 * el metodo guardarProducto redirecciona a nuevo_producto
	 * 
	 * @param model
	 * @param producto
	 * @return String
	 * @throws IOException
	 */
	@PostMapping("/guardar")
	public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, @RequestParam(name = "file") MultipartFile image) throws IOException {
		ModelAndView modelview = new ModelAndView("creacion_de_productos");
		if (result.hasErrors()) {
			modelview.setViewName("nuevo_producto");
			modelview.addObject("producto", producto);
			return "nuevo_producto";
		}
		if (!image.isEmpty()) {
            String uniqueFilename = uploadFileService.copy(image);
            producto.setImagen(uniqueFilename);
			LOGGER.info("url de imagen:---------->"+producto.getImagen());
        }        
		// manejaListas.getLista().add(producto);
		this.productoService.guardarProducto(producto);
		return "redirect:/producto/listado";
	}

	@GetMapping("/editar/{codigo}")
	public String modificar(Model model, @PathVariable(value = "codigo") int codigo) {
		this.titulo = "Editar Producto";
		// Producto p = manejaListas.buscarProductoporNombre(nombre);
		Producto p = this.productoService.buscarProductoByCodigo(codigo);
		model.addAttribute("producto", p);
		// en q lugar de la lista se encuentra el objeto a editar
		// int indice = manejaListas.getLista().indexOf(p);
		int indice = this.productoService.getProductos().indexOf(p);
		model.addAttribute("edicion", true);
		model.addAttribute("indice", indice);
		model.addAttribute("titulo", this.titulo);
		return "nuevo_producto";
	}

	@PostMapping("/editar/{indice}")
	public String modificarProducto(@Valid @ModelAttribute("producto") Producto producto,
			 BindingResult result,@PathVariable(value = "indice") int indice, 
			 @RequestParam(name = "file") MultipartFile image, Model model) throws IOException {
		System.out.println(indice);

		if (result.hasErrors()) {
			model.addAttribute("producto", producto);
			model.addAttribute("edicion", true);
			model.addAttribute("titulo", this.titulo);
			model.addAttribute("indice", indice);
			return "nuevo_producto";
		}

		Producto prod = productoService.buscarProductoByCodigo(producto.getCodigo());
        if (!image.isEmpty()) {
            if (prod.getImagen() != null && prod.getImagen().length() > 0)
                uploadFileService.delete(prod.getImagen());
            String uniqueFileName = uploadFileService.copy(image);
            producto.setImagen(uniqueFileName);
        } else {
            if (prod.getImagen() != null)
                producto.setImagen(prod.getImagen());
        }

		// manejaListas.getLista().set(indice, producto);
		// this.productoService.getProductos().set(indice, producto);
		this.productoService.modificarProducto(indice, producto);
		return "redirect:/producto/listado";
	}

	@GetMapping("/eliminar/{codigo}")
	public String eliminarProducto(@PathVariable(value = "codigo") int codigo) {
		// Producto p = manejaListas.buscarProductoporNombre(nombre);		
		// manejaListas.getLista().remove(p);
		Producto prod = productoService.buscarProductoByCodigo(codigo);
        if (prod.getImagen() != null && prod.getImagen().length()>0) {
            uploadFileService.delete(prod.getImagen());            
        }
		this.productoService.eliminarProductoByCodigo(codigo);
		return "redirect:/producto/listado";
	}


}
