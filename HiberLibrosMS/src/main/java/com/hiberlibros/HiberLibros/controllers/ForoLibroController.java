package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.ForoLibro;
import com.hiberlibros.HiberLibros.interfaces.IForoLibroService;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/foros")
public class ForoLibroController {

    @Autowired
    private IForoLibroService serviceForoLibro;

    @Autowired 
    private ISeguridadService serviceSeguridad;
    
    @Autowired
    private IUsuarioService usuService;
    
    @Autowired
    private ILibroService serviceLibro;
    
    @GetMapping("/libro")
    public String recuperarForosPorLibro(Model m, Integer id) {
        m.addAttribute("foros",serviceForoLibro.recuperarForosDeLibro(serviceLibro.libroId(id)));
        return "/principal/foro";
    }
    
    @GetMapping()
    public String recuperarForos(Model m) {
        m.addAttribute("foro", new ForoLibro());
        m.addAttribute("libros", serviceLibro.encontrarDisponible());
        m.addAttribute("foros", serviceForoLibro.recuperarTodosLosForos());
        return "/principal/foro";
    }
    
    @GetMapping("/alta")
    public String altaForo (Model m, ForoLibro l){
        l.setDesactivado(Boolean.FALSE);
        l.setUsuarioCreador(usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext()));
        serviceForoLibro.altaForoLibro(l);
        m.addAttribute("foro", new ForoLibro());
        m.addAttribute("libros", serviceLibro.encontrarDisponible());
        m.addAttribute("foros", serviceForoLibro.recuperarTodosLosForos());

        return "/principal/foro";
    }
    
    
    @GetMapping("/baja")
    public String bajaForo (Integer id){
        serviceForoLibro.bajaForoLibro(id);
        return "/principal/altaForo";
    }
    
    
    
    
}
