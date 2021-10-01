package com.hiberlibros.HiberLibros.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hiberlibros.HiberLibros.dtos.AutorDto;
import com.hiberlibros.HiberLibros.dtos.LibrosAutorDto;
import com.hiberlibros.HiberLibros.dtos.ListaAutorDto;
import com.hiberlibros.HiberLibros.entities.Autor;

@FeignClient(contextId = "sautor", name="HiberLibrosBack")
@RequestMapping
public interface AutorFeign {
	
	@GetMapping("/autores/listarAdmin")
    public ListaAutorDto listaAdmin(@RequestParam String borrado);
	
	@GetMapping("/editarAutor")
    public AutorDto editarAutor(@RequestParam Integer id);
	
	@GetMapping("/guardarAutor")
    public void guardarAutor(@RequestParam Autor autor);
	
	@GetMapping("/eliminarAutor")
    public void eliminarAutorAdmin(@RequestParam Integer id);

	@GetMapping("/librosAutor")
    public LibrosAutorDto LibrosDeAutor(@RequestParam Integer id);
}
