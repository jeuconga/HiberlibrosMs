package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Isabel
 */
@Controller
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private IGeneroService serviceGen;

    @GetMapping
    public String verGeneros(Model model) {
        model.addAttribute("generos", serviceGen.getGeneros());
        model.addAttribute("generoForm", new Genero());

        return "/generos/genero";
    }

    @PostMapping("/guardar")
    public String formulario(Genero genero) {
        serviceGen.guardarGenero(genero);

        return "redirect:listarAdmin";
    }

    @GetMapping("/borrar/{id}")
    public String borrarGenero(Model m, @PathVariable Integer id) {
        String borrado = "";
        if (serviceGen.borrarGenero(id)) {
            borrado = "Género borrado";
        } else {
            borrado = "Error, no es posible borrar este género";
        }

        return "redirect:/genero/listarAdmin?borrado=" + borrado;
    }

    @GetMapping("/editar")
    @ResponseBody
    public GeneroDto editarGenero(Integer id) {
        Genero editGenero = serviceGen.encontrarPorId(id);
        GeneroDto genDto = new GeneroDto(editGenero.getId(), editGenero.getNombre());
        return genDto;
    }

    @GetMapping("/listarAdmin")
    private String listarTodo(Model m, String borrado) {
        m.addAttribute("generos", serviceGen.getGeneros());
        m.addAttribute("generoForm", new Genero());
        if (borrado != null) {
            m.addAttribute("borrado", borrado);
        }
        return "/administrador/generos";
    }
}
