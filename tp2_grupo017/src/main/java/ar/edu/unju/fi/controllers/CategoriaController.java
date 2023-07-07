package ar.edu.unju.fi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.services.ICategoriaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    @Qualifier("categoriaServiceMysql")
    private ICategoriaService categoriaService;

    /**
     * Méotod que recupera un listado de objetos del tipo categoría y los muestra en
     * en la vista.
     * 
     * @param model parametro de tipo Model que es usado para enviar los datos a la
     *              vista.
     * @return retorna u Strin que es el nombre de la vista.
     */
    @GetMapping("/listado")
    public String getCategoriaPage(Model model) {
        List<Categoria> listado = categoriaService.getCategorias();
        model.addAttribute("titulo", "Categorias");
        model.addAttribute("tituloForm", "Listado de Categorías");
        model.addAttribute("categorias", listado);
        return "gestion_categorias";

    }

    /**
     * Método que nos redirige al formulario donde se crea una nueva categoría.
     * 
     * @param model parametro del tipo Model para enviar una serie de datos entre el
     *              controller y la vista.
     * @return un String que es el formualario.
     */
    @GetMapping("/nuevo")
    public String getNuevaCategoriaPage(Model model) {
        model.addAttribute("categoria", categoriaService.getCategoria());
        model.addAttribute("nuevo", true);
        model.addAttribute("titulo", "Nueva Categoria");
        model.addAttribute("tituloForm", "Registro de nueva categoría");
        return "nuevo_categoria";
    }

    /**
     * Método que guarda una categoría en la base de datos y retorna a la vista de gestion de categorías.
     * 
     * @param categoria Objeto del tipo Categoria que proviene de la vista por medio del @ModelAttribute 
     * y sujeto a ser validado mediante @Valid definido en las reglas de validacion en el model.
     * @param result Parametro del tipo BindingResult que almacena y recupera los errores de validación.
     * @param model parametro del tipo Model que se usa para pasar datos desde la vista al controller.
     * @return devuelve un String que si no hay error de validación te redirecciona a la página de gestión 
     * de categorías, sino si hay error entonces te retorna al formulario.
     */
    @PostMapping("/guardar")
    public String guardarCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("nuevo", true);
            model.addAttribute("titulo", "Nueva Categoria");
            model.addAttribute("tituloForm", "Registro de nueva categoría");
            return "nuevo_categoria";
        }
        categoria.setEstado(true);
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias/listado";
    }

    /**
     * Método que recupera un objetodel tipo Categoria según su ID, y envía el objeto al formulario
     * ára su edición.
     * 
     * @param id Parametro de tipo Long extraido de la URL que es el ID de la categoría a buscar.
     * @param model Parametro de tipo Model usado para pasar datos entre la vista y el controller.
     * @return un String que es el nombre de la vista. que en este caso es el formulario.
     */
    @GetMapping("/editar/{id}")
    public String editarCategoriaPage(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("titulo", "Editar categoria");
        model.addAttribute("tituloForm", "Editar Categoria");
        model.addAttribute("nuevo", false);
        model.addAttribute("categoria", categoriaService.getCateriaById(id));
        return "nuevo_categoria";
    }

    /**
     * Método que elimina una categoría según su ID.
     * 
     * @param id parametro de tipo Long que es el ID de la categoría a eliminar.
     * @return  un String que es el nombre de la vista de gestión de categorías.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable(value = "id") Long id) {
        categoriaService.eliminarCategoriaById(id);
        return "redirect:/categorias/listado";
    }
}
