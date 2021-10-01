package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.entities.Genero;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@FeignClient(contextId = "sgenero", name="HiberLibrosBack")
@RequestMapping("/generoback")
public interface GeneroFeign {
    
    @GetMapping //Muestra la lista de generos
    public List<Genero> verGeneros();
    
//    //@SpringQueryMap
//     @PostMapping("/guardar")
//    public void formulario(@SpringQueryMap Genero genero);
}
