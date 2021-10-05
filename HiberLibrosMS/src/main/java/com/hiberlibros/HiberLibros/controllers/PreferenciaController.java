package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Preferencia;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
import com.hiberlibros.HiberLibros.interfaces.IPreferenciaService;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Isabel
 */
@Controller
@RequestMapping("/preferenciaback")
public class PreferenciaController {

    @Autowired
    private IPreferenciaService prefService;
    @Autowired
    private IGeneroService serviceGenero;
    @Autowired
    private IUsuarioService usuServ;
    @Autowired
    private ISeguridadService serviceSeguridad;

    @GetMapping
    public String verPreferencias(Model model) {
        Usuario u = usuServ.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        model.addAttribute("preferencias", prefService.findByUsuario(u));
        model.addAttribute("generos", serviceGenero.getGeneros());;
        model.addAttribute("formulario", new Preferencia());

        return "/preferencias/preferencia";
    }

    @PostMapping("/guardar")
    public String anadirPreferencia(Integer id_genero) {
        
        Usuario u = usuServ.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        Genero gen = serviceGenero.encontrarPorId(id_genero);
        Preferencia pref = new Preferencia();
        pref.setGenero(gen);
        pref.setUsuario(u);
       
        prefService.addPreferencia(pref);

        return "redirect:/preferencia";
    }

    @GetMapping("/borrar/{id}")
    public String borrarPreferencia(@PathVariable Integer id) {
        
        
        prefService.borrarPreferencia(id);

        return "redirect:/preferencia";
    }
}
