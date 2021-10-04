package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.dtos.VerGenerosDto;
import com.hiberlibros.HiberLibros.entities.Genero;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "sgenero", name="HiberLibrosBack", url="http://localhost:8092")
@RequestMapping("/generoback")
public interface GeneroFeign {
    
    /*@GetMapping //Muestra la lista de generos
    public List<Genero> verGeneros();*/
    
    @GetMapping
    public VerGenerosDto verGeneros();
    
    @PostMapping("/guardar")
    public String formulario(@SpringQueryMap GeneroDto genero);
    
    @GetMapping("/editar")
    public GeneroDto editarGenero(@RequestParam Integer id);
    
    @GetMapping("/borrar")
    public void borrarGenero(@RequestParam Integer id);
    
    
}
