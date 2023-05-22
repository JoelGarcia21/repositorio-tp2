package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsejosController {
	String titulo;
	
	@GetMapping("/consejos")
	public String getConsejos(Model model) {
		this.titulo = "Consejos de salud";
		model.addAttribute("titulo", this.titulo);
		return "creacion_consejos_de_salud";
	}

}
