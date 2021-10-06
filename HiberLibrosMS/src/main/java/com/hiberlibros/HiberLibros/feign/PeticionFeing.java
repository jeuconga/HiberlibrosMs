/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.PeticionDto;
import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import com.hiberlibros.HiberLibros.entities.Peticion;
import com.hiberlibros.HiberLibros.entities.Usuario;
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
@FeignClient(contextId = "sPeticion", name = "HiberLibrosBack", url="http://localhost:8092")
@RequestMapping("/peticionback")
public interface PeticionFeing {
    
    @GetMapping(value = "/peticion")
    public PeticionDto consultaTodasPeticiones(@SpringQueryMap PeticionDto p);
    
    @GetMapping(value = "/alta") //Recibe los integer y crea una nueva petición, vuelve al panel de usuario
    public String peticionAlta(@RequestParam Integer id_ul,@RequestParam String email);
    
    
    @PostMapping(value = "/baja")
    public void peticionBaja(@SpringQueryMap PeticionDto p);
    
    
    @GetMapping("/baja") //retira una solicitud solo con el ID de la petición para no tener que mandar un objeto petición
    public void retirarSolicitud(@RequestParam Integer id);
    
    
    @PostMapping(value = "/aceptar")
    public void aceptarPeticion(@SpringQueryMap PeticionDto p,@SpringQueryMap UsuarioDto u);
    
       @PostMapping(value = "/rechazar")
    public void rechazarPeticion(@RequestParam Integer id,@SpringQueryMap UsuarioDto u);
    
    
    @PostMapping(value = "/peticionesPendientes")
    public UsuarioDto consultarTodasPeticionesPendientes(@SpringQueryMap UsuarioDto u);
    

    @PostMapping(value = "/modificacion")
    public void peticionModificacion(PeticionDto p);
    
    
}
