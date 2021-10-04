package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.interfaces.IAutorService;
import com.hiberlibros.HiberLibros.interfaces.IEditorialService;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/librosback")
public class LibroController {

    @Autowired
    private ILibroService libroService;
    @Autowired
    private IGeneroService serviceGen;
    @Autowired
    private IEditorialService serviceEdit;
    @Autowired
    private IAutorService serviceAutor;

    @GetMapping("/libros")
    public Map<String,Object> mostrarFormulario() {
        Map<String,Object> m=new HashMap<>();
        m.put("libros", libroService.encontrarDisponible());
        m.put("generos", serviceGen.getGeneros());
        m.put("editoriales", serviceEdit.consultaTodas());
        m.put("autores", serviceAutor.consultarAutores());
        return m;
    }

    @PostMapping("/guardar")
    public void guardarLIbro(Model m, Libro libro, Integer id_genero, Integer id_editorial, Integer id_autor) {
        libro.setGenero(serviceGen.encontrarPorId(id_genero));
        libro.setEditorial(serviceEdit.encontrarPorId(id_editorial));
        libro.setAutor(serviceAutor.encontrarAutor(id_autor).get());
        libroService.guardarLibro(libro);
        
    }

    @GetMapping("/eliminar")
    public String eliminarLibro(Model m, Integer id) {
        if (libroService.bajaLibroId(id)) {
            m.addAttribute("borrado", "Libro borrado");
        } else {
            m.addAttribute("borrado", "Error, no es posible borrar este libro");
        }
        return "redirect:libros";
    }

    @GetMapping("/modificar")
    public String modificarLibro(Model m, Integer id) {

        m.addAttribute("imagen", libroService.libroId(id).getUriPortada());
        m.addAttribute("libro", libroService.libroId(id));
        m.addAttribute("generos", serviceGen.getGeneros());
        m.addAttribute("editoriales", serviceEdit.consultaTodas());
        m.addAttribute("autores", serviceAutor.consultarAutores());

        return "/libros/modificar";
    }

    @GetMapping("/listarAdmin")
    private String listarTodo(Model m, String borrado) {
        m.addAttribute("libros", libroService.encontrarDisponible());
        m.addAttribute("generos", serviceGen.getGeneros());
        m.addAttribute("editoriales", serviceEdit.consultaTodas());
        m.addAttribute("autores", serviceAutor.consultarAutores());
        if(borrado!=null){
            m.addAttribute("borrado",borrado);
        }

        return "/administrador/libros";
    }

    @PostMapping("/guardarAdmin")
    public String guardarAdmin(Model m, Libro libro, Integer id_genero, Integer id_editorial, Integer id_autor) {
        libro.setGenero(serviceGen.encontrarPorId(id_genero));
        libro.setEditorial(serviceEdit.encontrarPorId(id_editorial));
        libro.setAutor(serviceAutor.encontrarAutor(id_autor).get());
        libroService.guardarLibro(libro);
        
        return "redirect:listarAdmin";
    }

    @GetMapping("/eliminarAdmin")
    public String eliminarAdmin(Integer id) {
        String borrado="";
        if (libroService.bajaLibroId(id)) {
            borrado="Borrado con éxito";
        } else {
           borrado= "Error, no es posible borrar este autor";
        }
        return "redirect:listarAdmin?borrado="+borrado;
    }

    @PostMapping("/addValoracionLibro")
    public String addValoracionLibro(Model m, Integer id, Integer valoracion) {
        libroService.valorarLibro(libroService.libroId(id), valoracion);

        return "redirect:/hiberlibros/buscarLibro";
    }
}
