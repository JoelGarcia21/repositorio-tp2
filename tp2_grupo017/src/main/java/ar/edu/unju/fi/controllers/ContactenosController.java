package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.entity.ContactenosModel;
import jakarta.validation.Valid;

    @Controller
    public class ContactenosController {    

    @GetMapping("/contactenos")
	public String getContactenos(Model model) {
		    model.addAttribute("contactenosModel", new ContactenosModel());
		    return "contactenos";
	}
	
	
	@PostMapping("/enviar")
	public String enviarFormulario(@ModelAttribute("contactenosModel") @Valid ContactenosModel contactenosModel,
            BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
		return "contactenos";
		}

			// Procesar el formulario y enviar el mensaje

		return "redirect:/contactenos";
}
}
