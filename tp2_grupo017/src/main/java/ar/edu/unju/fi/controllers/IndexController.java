package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

	@GetMapping("/")
	public String getIndex(Model model) {
		String titulo = "Pata de oro";
		model.addAttribute("titulo", titulo);
		return "index";
	}
}
