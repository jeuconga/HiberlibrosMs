/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.UsuarioSeguridadDtoFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "sUsuariosSeguridad", name = "HiberLibrosBack",  url="http://localhost:8092/")
@RequestMapping("/usuarioSeguridadback")
public interface UsuarioSeguridadFeign {
    
    @GetMapping
    public UsuarioSeguridadDtoFeign usuarioSeguridadSecurity(@RequestParam Integer id);
    
}
