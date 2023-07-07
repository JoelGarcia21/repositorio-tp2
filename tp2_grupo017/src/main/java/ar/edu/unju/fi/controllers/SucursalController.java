package ar.edu.unju.fi.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Provincia;
import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.services.IProvinciaService;
import ar.edu.unju.fi.services.ISucursalService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {
	
	@Autowired
	@Qualifier("sucursalServiceMysql")
	private ISucursalService sucursalService;

	@Autowired
	@Qualifier("provinciaServiceMysql")
	private IProvinciaService provinciaService;
	
	/**
	 * Método que renderiza la Página de sucursales.
	 * @param model
	 * @return pagina html sucursales.
	 */
	@GetMapping("/listado")
	public String getListaSucursalesPage (Model model) {
		model.addAttribute("titulo", "Sucursales");
		model.addAttribute("sucursales", sucursalService.getLista()); 

		return "sucursales_page";
	}

	/**
	 * Método que renderiza la página de gestión de sucursales.
	 * @param model
	 * @return página html de gestion de sucursales.
	 */
	@GetMapping("/gestion")
	public String getListaSucursalesGestionPage(Model model) {
		model.addAttribute("titulo", "Gestión | Sucursales");
		model.addAttribute("sucursales", sucursalService.getLista());

		return "sucursales";
	}

	
	
	/**
	 * Método que renderiza la página de formulario de nueva sucursal.
	 * @param model
	 * @return página de registro de sucursal.
	 */
	@GetMapping("/nuevo")
	public String getNuevaSucursalPage(Model model) {
		boolean edicion = false;
		model.addAttribute("titulo", "Nueva Sucursal");
		model.addAttribute("sucursal", sucursalService.getSucursal());
		model.addAttribute("edicion", edicion);
		model.addAttribute("provincias", provinciaService.getByEstado(true));

		return "nueva_sucursal";
	}
	
	
	/**
	 * Método que realiza el registro en la base de datos de una sucursal.
	 * @param sucursal a guardar
	 * @param result
	 * @return página html de registro de sucursal.
	 */
	@PostMapping("/guardar")
	public ModelAndView getGuardarSucursalPage(@Valid @ModelAttribute("sucursal") Sucursal sucursal, BindingResult result) {
		ModelAndView modelView = new ModelAndView("sucursales");
		if(result.hasErrors()) {
			modelView.setViewName("nueva_sucursal");
			modelView.addObject("titulo", "Nueva Sucursal");
			modelView.addObject("sucursal", sucursal);
			modelView.addObject("provincias", provinciaService.getByEstado(true));
			return modelView;
		}
		Provincia provincia = provinciaService.getByCodigo(sucursal.getProvincia().getCodigo());
		sucursal.setProvincia(provincia);
		sucursal.setEstado(true);		
		sucursalService.guardar(sucursal);
		modelView.addObject("sucursales", sucursalService.getLista());
		modelView.addObject("titulo", "Gestión | Sucursales");
		return modelView;
	}
	

	/**
	 * Método que realiza la busqueda en la base de datos de la sucursal y 
	 * envia los datos al formulario para su edición.
	 * @param model
	 * @param codigo de la sucursal a editar.
	 * @return pagina de registro de sucursal.
	 */
	@GetMapping("/modificar/{codigo}")
	public String getModificarSucursalPage(Model model, @PathVariable(value="codigo") Long codigo) {
		Sucursal sucursalEncontrada = sucursalService.getBy(codigo);
		boolean edicion = true;
		model.addAttribute("sucursal", sucursalEncontrada);
		model.addAttribute("provincias", provinciaService.getByEstado(true));
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Editar Sucursal");

		return "nueva_sucursal";
	}
	
	
	/**
	 * Método que realiza la actualización de la sucursal en la base de datos.
	 * @param sucursal a actualizar.
	 * @param result
	 * @param model
	 * @return a la página de gestión de sucursales.
	 */
	@PostMapping("/modificar")
	public String modificarSucursal(@Valid @ModelAttribute("sucursal")Sucursal sucursal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			boolean edicion = true;
			model.addAttribute("sucursal", sucursal);
			model.addAttribute("provincias", provinciaService.getByEstado(true));
			model.addAttribute("edicion", edicion);
			model.addAttribute("titulo", "Editar Sucursal");

			return "nueva_sucursal";
		}
		Provincia provincia = provinciaService.getByCodigo(sucursal.getProvincia().getCodigo());
		sucursal.setProvincia(provincia);
		sucursal.setEstado(true);
		sucursalService.guardar(sucursal);
		
		return "redirect:/sucursal/gestion";
	}
		
	/**
	 * Método que realiza el cambio de estado de la sucursal
	 * @param codigo de la sucursal a cambiar el estado.
	 * @return la página de gestión de sucursales.
	 */
	@GetMapping("/eliminar/{codigo}")
	public String eliminarSucursal(@PathVariable(value="codigo") Long codigo) {
		// for(Sucursal sucu: sucursalService.getLista()) {
		// 	if(sucu.getNombre().equals(nombre)) {
		// 		sucursalService.eliminar(sucu);
		// 		break;
		// 	}
		// }
		sucursalService.eliminar(codigo);

		return "redirect:/sucursal/gestion";
	}

	/**
	 * Método que realiza la busqueda de sucursales según un rango de fechas.
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param model
	 * @return la página con la lista de sucursales filtradas.
	 */
	@GetMapping("/buscar")
	public String buscarSucursales(
			@RequestParam(value = "inicio") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fechaInicial,
			@RequestParam(value = "final") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fechaFinal, Model model) {
		
		
		if (fechaInicial.isBefore(fechaFinal)) {			
			model.addAttribute("error", false);
			model.addAttribute("sucursales", this.sucursalService.getSucursalBeetwen(fechaInicial, fechaFinal));			
		}else {
			model.addAttribute("error", true);			
		}
		model.addAttribute("titulo", "Sucursales");
		return "sucursales_page";
	}
}
//
