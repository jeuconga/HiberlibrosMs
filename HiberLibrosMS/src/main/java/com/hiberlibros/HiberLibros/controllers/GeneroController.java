package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.feign.generoDto.VerGenerosDto;
import com.hiberlibros.HiberLibros.feign.GeneroFeign;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private IGeneroService serviceGen;

    @Autowired
    private GeneroFeign genFeign;

    //FUNCIONA OK
    @GetMapping
    public String verGeneros(Model model) {
        VerGenerosDto res = genFeign.verGeneros();
        model.addAttribute("generos", res.getGeneros());
        model.addAttribute("generoForm", res.getGeneroForm());

        return "/generos/genero";
    }

    //FUNCIONA OK
    @PostMapping("/guardar")
    public String formulario(GeneroDto genero) {
        genFeign.formulario(genero);

        return "redirect:listarAdmin";
    }

    //FUNCIONA OK
    @GetMapping("/editar")
    @ResponseBody
    public GeneroDto editarGenero(Integer id) {
        GeneroDto gen = genFeign.editarGenero(id);
        return gen;
    }

    //FUNCIONA OK
    @GetMapping("/borrar/{id}")
    public String borrarGenero(Model m, @PathVariable Integer id) {
        String borrado = "";
        if (genFeign.borrarGenero(id)) {
            borrado = "Género borrado";
        } else {
            borrado = "Error, no es posible borrar este género";
        }
        return "redirect:/genero/listarAdmin?borrado=" + borrado;
    }

    //FUNCIONA OK
    @GetMapping("/listarAdmin")
    private String listarTodo(Model m, String borrado) {
        VerGenerosDto res = genFeign.verGeneros();
    
        m.addAttribute("generos", res.getGeneros());
        m.addAttribute("generoForm", res.getGeneroForm());
        if (borrado != null) {
            m.addAttribute("borrado", borrado);
        }
        return "/administrador/generos";
    }
}
