/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.ComentarioForo;
import com.hiberlibros.HiberLibros.interfaces.IComentarioForoService;
import com.hiberlibros.HiberLibros.interfaces.IForoLibroService;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.l;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("hilos")
public class ComentarioForoController {
    @Autowired
    private IComentarioForoService serviceComentarioForo; 
    
    @Autowired
    private IForoLibroService serviceForoLibroService;
    
    @Autowired
    private IUsuarioService usuService;
    
    @Autowired 
    private ISeguridadService serviceSeguridad;
    
    @GetMapping("/consultarPorForo")
    public String consultarComentariosPorForo(Model m, Integer idForo){

        m.addAttribute("foro", serviceForoLibroService.consultarForo(idForo));
        m.addAttribute("comentarios",serviceComentarioForo.consultarComentariosPorForo(serviceForoLibroService.consultarForo(idForo)));
        //m.addAttribute("comentarios",serviceComentarioForo.consultarComentariosPorForo(idForo));
        return "principal/comentarios";
    }
    
    @PostMapping("/alta")
    public String altaComentario(Model m, Integer idForoLibro, String comentario){
        
        ComentarioForo comentarioForo =  new ComentarioForo();
        comentarioForo.setComentarioForo(comentario);
        comentarioForo.setUsuarioComentario(usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext()));
        comentarioForo.setForoLibro(serviceForoLibroService.consultarForo(idForoLibro));
        serviceComentarioForo.altaComentario(comentarioForo);
        
        m.addAttribute("foro", serviceForoLibroService.consultarForo(idForoLibro));
        m.addAttribute("comentarios",serviceComentarioForo.consultarComentariosPorForo(serviceForoLibroService.consultarForo(idForoLibro)));
        
        return "principal/comentarios";
    }
    
}
