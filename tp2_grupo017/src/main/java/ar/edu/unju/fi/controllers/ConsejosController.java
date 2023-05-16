package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsejosController {
	
	@GetMapping("/consejos")
	public String getConsejos() {
		
		return "creacion_consejos_de_salud";
	}

}
