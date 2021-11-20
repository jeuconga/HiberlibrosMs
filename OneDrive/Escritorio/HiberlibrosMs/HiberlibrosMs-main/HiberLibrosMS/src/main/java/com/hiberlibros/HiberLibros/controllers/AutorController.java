package com.hiberlibros.HiberLibros.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hiberlibros.HiberLibros.dtos.LibroDto;
import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.interfaces.IAutorService;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.repositories.AutorLibroRepository;
import java.util.stream.Collectors;

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

    @GetMapping("/autorLista")
    public String lista(Model m) {
        m.addAttribute("autores", autorService.consultarAutores());
        return "autores/lista";
    }

    @GetMapping("/autorForm")
    public String read(Model m) {
        m.addAttribute("autor", new Autor());
        return "autores/autorForm";
    }

    @GetMapping("/autorForm/{id}")
    public String find(Model m, @PathVariable Integer id) {
        m.addAttribute("autor", autorService.encontrarAutor(id));
        return "autores/autorForm";
    }

    @PostMapping("/saveAutor")
    public String insertarAutor(Autor autor) {
        autorService.guardarAutor(autor);
        return "redirect:hiberlibros/paneladmin";
    }

    @GetMapping("/deleteAutor/{id}")
    public String delete(@PathVariable Integer id) {
        autorService.borrarAutor(id);
        return "redirect:/autorLista";
    }

    @GetMapping("/getLibrosAutor")
    @ResponseBody
    public List<LibroDto> getLibros(Integer id) {
        return (List<LibroDto>) repo.findAll()
                .stream()
                .filter(z -> z.getAutor().getIdAutor() == id)
                .map(x -> obj.map(x.getLibro(), LibroDto.class))
                .collect(Collectors.toList());
    }

    public List<LibroDto> consultarLibros(Integer id) {
        return autorService.getLibros(id);

    }

    @GetMapping("/autores/listarAdmin")
    public String listaAdmin(Model m, String borrado) {
        m.addAttribute("autores", autorService.consultarAutores());
        if(borrado!=null){
            m.addAttribute("borrado", borrado);
        }
        return "administrador/autores";
    }

    @GetMapping("/librosAutor")
    public String LibrosDeAutor(Model m, Integer id) {
        Autor a = autorService.encontrarAutor(id).get();

        m.addAttribute("libros", ilibroservice.encontrarPorAutorActivos(a));
        return "administrador/librosAutor";
    }

    @GetMapping("/editarAutor")
    public String editarAutor(Model m, Integer id) {
        m.addAttribute("autor", autorService.encontrarAutor(id).get());
        return "administrador/editAutor";
    }

    @PostMapping("/guardarAutor")
    public String guardarAutor(Model m, Autor autor) {
        autorService.guardarAutor(autor);
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
