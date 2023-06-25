package ar.edu.unju.fi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.services.ICategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    @Qualifier("categoriaServiceMysql")
    private ICategoriaService categoriaService;

    @GetMapping("/listado")
    public String getCategoriaPage(Model model){
        List<Categoria> listado = categoriaService.getCategorias();
        model.addAttribute("titulo", "Categorias");
        model.addAttribute("tituloForm", "Listado de Categorías");
        model.addAttribute("categorias", listado);
        return "gestion_categorias";

    }

    @GetMapping("/nuevo")
    public String getNuevaCategoriaPage(Model model){
        model.addAttribute("categoria", categoriaService.getCategoria());
        model.addAttribute("nuevo", true);
        model.addAttribute("titulo", "Nueva Categoria");
        model.addAttribute("tituloForm", "Registro de nueva categoría");
        return "nuevo_categoria";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute("categoria")Categoria categoria){
        categoria.setEstado(true);
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoriaPage(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("titulo", "Editar categoria");
        model.addAttribute("tituloForm", "Editar Categoria");
        model.addAttribute("nuevo", false);
        model.addAttribute("categoria", categoriaService.getCateriaById(id));
        return "nuevo_categoria";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable(value = "id")Long id){
        categoriaService.eliminarCategoriaById(id);
        return "redirect:/categorias/listado";
    }
}
