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
    public void guardarLIbro( Libro libro, Integer id_genero, Integer id_editorial, Integer id_autor) {
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
    public Map<String,Object> modificarLibro(Integer id) {
        Map<String,Object> mapa=new HashMap<>();
        mapa.put("imagen", libroService.libroId(id).getUriPortada());
        mapa.put("libro", libroService.libroId(id));
        mapa.put("generos", serviceGen.getGeneros());
        mapa.put("editoriales", serviceEdit.consultaTodas());
        mapa.put("autores", serviceAutor.consultarAutores());

        return mapa;
    }

    @GetMapping("/listarAdmin")
    private  Map<String,Object>  listarTodo(Model m,String borrado) {
        Map<String,Object> mapa=new HashMap<>();
        mapa.put("libros", libroService.encontrarDisponible());
        mapa.put("generos", serviceGen.getGeneros());
        mapa.put("editoriales", serviceEdit.consultaTodas());
        mapa.put("autores", serviceAutor.consultarAutores());
        if(borrado!=null){
            m.addAttribute("borrado",borrado);
        }

        return mapa;
    }

    @PostMapping("/guardarAdmin")
    public String guardarAdmin(Libro libro, Integer id_genero, Integer id_editorial, Integer id_autor) {
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
    public String addValoracionLibro(Integer id, Integer valoracion) {
        libroService.valorarLibro(libroService.libroId(id), valoracion);

        return "redirect:/hiberlibros/buscarLibro";
    }
}
