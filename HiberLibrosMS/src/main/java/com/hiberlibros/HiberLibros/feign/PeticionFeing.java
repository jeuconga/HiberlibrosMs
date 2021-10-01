/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.entities.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@FeignClient(contextId = "sPeticion", name = "HiberLibrosBack")
@RequestMapping("/hiberlibrosback")
public interface PeticionFeing {
    @PostMapping(value = "/rechazar")
    public void rechazarPeticion(Integer id, Usuario u);
    
    
    @PostMapping(value = "/peticionesPendientes")
    public  consultarTodasPeticionesPendientes(@SpringQueryMap Usuario u);
    
   
    
}
