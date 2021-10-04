package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.VerGenerosDto;
import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.feign.GeneroFeign;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private IGeneroService serviceGen; 
    
    @Autowired
    private GeneroFeign genFeign;

    

   // private GeneroFeign genFeign;
 

    @GetMapping
    public String verGeneros(Model model) {
        VerGenerosDto res=genFeign.verGeneros();
        model.addAttribute("generos", res.getGeneros());
        model.addAttribute("generoForm", res.getGeneroForm());

        return "/generos/genero";
    }

//    @GetMapping
//    public String verGeneros(Model model) {
//        genFeign.verGeneros();
//        
//        return "/generos/genero";
//    }


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

//    @GetMapping("/editar")
//    public GeneroDto editarGenero(Integer id) {
//        GeneroDto gen = genFeign.editarGenero(id);
//        gen = new GeneroDto(gen.getId(), gen.getNombre());
//        return gen;
//    }

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
