/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.feign.uasuariodto.UsuarioFormularioDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(contextId = "sUsuarios", name = "HiberLibrosBack",  url="http://localhost:8092/")
@RequestMapping("/usuariosback")
public interface UsuarioFeign {
    
    @GetMapping
    public UsuarioFormularioDto usuarioFormulario(@RequestParam String registro);
    
    @PostMapping("/guardarUsuario")
    public String usuarioRegistrar(@SpringQueryMap UsuarioDto u,@RequestParam String password);
    
    @PostMapping("/editarUsuario")//edita usuario, manda el usuario para rellenar el formulario
    public void usuarioEditar(@SpringQueryMap Usuario u);
    
    @GetMapping("/borrar")
    public Boolean borrar(@RequestParam Integer id);
    
    @GetMapping("/listarAdmin")
    public List<UsuarioDto> listarTodo();
    
    @PostMapping("/altaAdmin")
    public String altaAdmin(@SpringQueryMap Usuario u, @RequestParam String password);
    
    @PostMapping(value = "/imagenPerfil", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void imagenPerfil(MultipartFile ficheroImagen, @RequestParam Integer id);
    
    @GetMapping("/download")
    public ResponseEntity<Resource> mostrarImagen(@RequestParam String imagen);
    
    @GetMapping("/usuarioSeguridadMail")
    public UsuarioDto usuarioSeguridadMail(@RequestParam String mail);
    
    
}
