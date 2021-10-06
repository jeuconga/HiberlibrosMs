package com.hiberlibros.HiberLibros.feign;


import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.feign.generoDto.VerGenerosDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "sgenero", name="HiberLibrosBack", url="http://localhost:8092")
@RequestMapping("/generoback")
public interface GeneroFeign {
    
    @GetMapping
    public VerGenerosDto verGeneros();
    
    @PostMapping("/guardar")
    public void formulario(@SpringQueryMap GeneroDto genero);
    
    @GetMapping("/editar")
    public GeneroDto editarGenero(@RequestParam Integer id);
    
    @GetMapping("/borrar/{id}")
    public Boolean borrarGenero(@RequestParam @PathVariable Integer id);
}
