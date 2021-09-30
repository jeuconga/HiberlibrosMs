/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.TablaLibrosDto;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "s1", name = "HiberLibrosBack")
@RequestMapping("/hiberlibrosback")
public interface InicioFeign {
    
    @GetMapping("/buscarLibro")//Muestra la lita de libros, todos o los buscados si está relleno el campo buscador
    public Map<String, Object> buscarLibro(@RequestParam Integer id, @RequestParam String buscador, @RequestParam String email);
    @GetMapping("/tablaBuscarLibro")//Muestra la lita de libros, todos o los buscados si está relleno el campo buscador
    public List<TablaLibrosDto> tablaBuscarLibro(@RequestParam String email);
    
    
}
