package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unju.fi.models.Sucursal;
import ar.edu.unju.fi.services.ISucursalService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sucursal")
public class SucursalesController {
	
	@Autowired
	private ISucursalService sucursalService;
	
	@GetMapping("/listado")
	public String getListaSucursalesPage (Model model) {
		model.addAttribute("sucursales", sucursalService.getLista()); 

		return "sucursales";
	}

	@GetMapping("/nuevo")
	public String getNuevaSucursalPage(Model model) {
		boolean edicion = false;

		model.addAttribute("sucursal", sucursalService.getSucursal());
		model.addAttribute("edicion", edicion);

		return "nueva_sucursal";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView getGuardarSucursalPage(@Valid @ModelAttribute("sucursal") Sucursal sucursal, BindingResult result) {
		ModelAndView modelView = new ModelAndView("sucursales");
		if(result.hasErrors()) {
			modelView.setViewName("nueva_sucursal");
			modelView.addObject("sucursal", sucursal);
			return modelView;
		}
		sucursalService.guardar(sucursal);
		modelView.addObject("sucursales", sucursalService.getLista());		
		return modelView;
	}
	
	@GetMapping("/modificar/{nombre}")
	public String getModificarSucursalPage(Model model, @PathVariable(value="nombre") String nombre) {
		Sucursal sucursalEncontrada = sucursalService.getBy(nombre);
		boolean edicion=true;
		model.addAttribute("sucursal", sucursalEncontrada);
		model.addAttribute("edicion", edicion);

		return "nueva_sucursal";
	}
	
	@PostMapping("/modificar")
	public String modificarSucursal(@ModelAttribute("sucursal")Sucursal sucursal) {
		for(Sucursal sucu: sucursalService.getLista()) {
			if(sucu.getNombre().equals(sucursal.getNombre())) {
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
		for(Sucursal sucu: sucursalService.getLista()) {
			if(sucu.getNombre().equals(nombre)) {
				sucursalService.eliminar(sucu);
				break;
			}
		}
		
		return "redirect:/sucursal/listado";
	}
}
//
