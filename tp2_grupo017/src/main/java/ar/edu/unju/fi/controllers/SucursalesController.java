package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.listados.ListaSucursal;
import ar.edu.unju.fi.models.Sucursal;

@Controller
@RequestMapping("/sucursal")
public class SucursalesController {
	
	ListaSucursal listaSucursales = new ListaSucursal();
	String titulo;
	
	@GetMapping("/listado")
	public String getListaSucursalesPage (Model model) {
		model.addAttribute("sucursales", listaSucursales.getSucursales()); //Falta completar listaSucursales
		this.titulo = "Sucursales";
		model.addAttribute("titulo", this.titulo);
		return "sucursales";
	}

	@GetMapping("/nuevo")
	public String getNuevaSucursalPage(Model model) {
		boolean edicion = false;
		this.titulo = "Nueva Sucursal";
		model.addAttribute("sucursal", new Sucursal());
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", this.titulo);
		return "nueva_sucursal";
	}
	@PostMapping("/guardar")
	public ModelAndView getGuardarSucursalPage(@ModelAttribute("sucursal") Sucursal sucursal) {
		ModelAndView modelView = new ModelAndView("sucursales");
		listaSucursales.getSucursales().add(sucursal);
		modelView.addObject("sucursales", listaSucursales.getSucursales());		
		return modelView;
	}
	
	@GetMapping("/modificar/{nombre}")
	public String getModificarSucursalPage(Model model, @PathVariable(value="nombre") String nombre) {
		Sucursal sucursalEncontrada = new Sucursal();
		boolean edicion=true;
		this.titulo = "Editar Sucursal";
		for(Sucursal sucu : listaSucursales.getSucursales()) {
			sucursalEncontrada = sucu;
			break;
		}
		model.addAttribute("sucursal", sucursalEncontrada);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", this.titulo);
		return "nueva_sucursal";
	}
	
	@PostMapping("/modificar")
	public String modificarSucursal(@ModelAttribute("sucursal")Sucursal sucursal) {
		for(Sucursal sucu: listaSucursales.getSucursales()) {
			if(sucu.getNombre().equals(sucursal.getNombre())) {
				//sucu = sucursal;
				sucu.setDireccion(sucursal.getDireccion());
				sucu.setEmail(sucursal.getEmail());
				sucu.setFechaInicio(sucursal.getFechaInicio());
				sucu.setProvincia(sucursal.getProvincia());
				sucu.setTelefono(sucursal.getTelefono());
			}
		}
		return "redirect:/sucursal/listado";
	}
		
	@GetMapping("/eliminar/{nombre}")
	public String eliminarSucursal(@PathVariable(value="nombre") String nombre) {
		for(Sucursal sucu: listaSucursales.getSucursales()) {
			if(sucu.getNombre().equals(nombre)) {
				listaSucursales.getSucursales().remove(sucu);
				break;
			}
		}
		
		return "redirect:/sucursal/listado";
	}
}
//
