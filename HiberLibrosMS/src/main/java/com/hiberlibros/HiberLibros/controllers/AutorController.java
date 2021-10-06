package com.hiberlibros.HiberLibros.controllers;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.dtos.LibrosAutorDto;
import com.hiberlibros.HiberLibros.dtos.VerAutoresDto;
import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.feign.AutorFeign;
import com.hiberlibros.HiberLibros.interfaces.IAutorService;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.repositories.AutorLibroRepository;

@Controller
@RequestMapping
public class AutorController {

    @Autowired
    private AutorLibroRepository repo;

    @Autowired
    private ILibroService ilibroservice;

    @Autowired
    private ModelMapper obj;

    @Autowired
    private IAutorService autorService;
    
    @Autowired
    private AutorFeign feignAutor;

    @GetMapping("/autores/listarAdmin")
    public String listaAdmin(Model m, String borrado) {
    	VerAutoresDto map = feignAutor.listaAdmin(borrado);
        m.addAttribute("autores", map.getAutores());
        if(borrado!=null){
            m.addAttribute("borrado", borrado);
        }
        return "administrador/autores";
    }

    @GetMapping("/librosAutor")
    public String LibrosDeAutor(Model m, Integer id) {
    	LibrosAutorDto map = feignAutor.LibrosDeAutor(id);
        m.addAttribute("libros", map.getLibros());
        return "administrador/librosAutor";
    }

    @GetMapping("/editarAutor")
    public String editarAutor(Model m, Integer id) {
        Autor autor =  feignAutor.editarAutor(id);
        m.addAttribute("autor", autor);
        return "administrador/editAutor";
    }
    @GetMapping("/newAutor")
    public String crearAutor(Model m, Integer id) {
        m.addAttribute("autor", new Autor());
        return "administrador/editAutor";
    }

    @PostMapping("/guardarAutor")
    public String guardarAutor(Model m, Autor autor) {
        feignAutor.guardarAutor(autor);
        return "redirect:autores/listarAdmin";
    }

    @GetMapping("/eliminarAutor")
    public String eliminarAutorAdmin(Model m, Integer id) {
        String borrado="";
        if (autorService.borrarAutor(id)) {
            borrado="Borrado con éxito";
        } else {
            borrado="Error, no es posible borrar este autor";
        }
        return "redirect:autores/listarAdmin?borrado="+borrado;
    }
}
