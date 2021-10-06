/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.ForosDto;
import com.hiberlibros.HiberLibros.dtos.RecuperacionLibrosForosDto;
import com.hiberlibros.HiberLibros.entities.ForoLibro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Usuario
 */
@FeignClient(contextId = "sForoLibro", name = "HiberLibrosBack", url="http://localhost:8092")
@RequestMapping("/forosback")
public interface ForoLibroFeign {
    @GetMapping("/libro")
    public ForosDto recuperarForosPorLibro(@RequestParam Integer id);
    
    @GetMapping()
    public RecuperacionLibrosForosDto recuperarForos();
    
    @GetMapping("/alta")
    public void altaForo(@RequestParam String tituloForo, @RequestParam Integer idLibro ,@RequestParam String email);
    
    @GetMapping("/baja")
    public void bajaForo(@RequestParam Integer id);
    
}
