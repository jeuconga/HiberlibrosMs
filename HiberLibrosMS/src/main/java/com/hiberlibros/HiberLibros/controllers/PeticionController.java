package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.PeticionDto;
import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.feign.PeticionFeing;
import com.hiberlibros.HiberLibros.interfaces.IPeticionService;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("peticion")
public class PeticionController {
    @Autowired
    private IPeticionService servicePeticion;
    @Autowired
    private IUsuarioService uService;
    @Autowired
    private ISeguridadService serviceSeguridad;
    
    @Autowired 
    private PeticionFeing feingPeticion;
    
    @GetMapping(value = "/peticion")
    public String peticion(Model m, PeticionDto p){
//        if (p.getId()!=null){
//            m.addAttribute("peticion", p);
//        }
//        m.addAttribute("peticiones",servicePeticion.consultaTodasPeticiones());
        m.addAttribute("peticiones",feingPeticion.consultaTodasPeticiones(p));
        return "/peticion/peticion";
    }
    
    @GetMapping(value = "/alta") //Recibe los integer y crea una nueva petición, vuelve al panel de usuario
    public String peticionAlta(Model m, Integer id_ul){
        Usuario u = uService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
//        Peticion p=new Peticion();
//        servicePeticion.insertaPeticion(p, id_ul, u); 
        feingPeticion.peticionAlta(id_ul, u.getMail());  // recuperamos idUsuario del contexto
        return "redirect:/hiberlibros/panelUsuario";
    }
    
    @PostMapping(value = "/baja")
    public String peticionBaja(Model m, PeticionDto p){
        feingPeticion.peticionBaja(p);
        return "redirect:/peticion/peticion";
    }
    @GetMapping("/baja") //retira una solicitud solo con el ID de la petición para no tener que mandar un objeto petición
    public String retirarSolicitud(Integer id){
        feingPeticion.retirarSolicitud(id);
        return "redirect:/hiberlibros/panelUsuario";//vuelve al panel
    }
    @PostMapping(value = "/modificacion")
    public String peticionModificacion(Model m, PeticionDto p){
        feingPeticion.peticionModificacion(p);
        return "redirect:/peticion/peticion";
    }
    
    @PostMapping(value = "/aceptar")
    public String aceptarPeticion(Model m, PeticionDto p, UsuarioDto u){
        feingPeticion.aceptarPeticion(p,u);
        return "redirect:/peticion/peticion";
    }
            
    @PostMapping(value = "/rechazar")
    public String rechazarPeticion(Integer id, UsuarioDto u){
        feingPeticion.rechazarPeticion(id, u);
        return "redirect:/peticion/peticion";
    }
    
    @PostMapping(value = "/peticionesPendientes")
    public String consultarTodasPeticionesPendientes(Model m,UsuarioDto u){
         m.addAttribute("peticionesPendientes", feingPeticion.consultarTodasPeticionesPendientes(u));
         return "redirect:/peticion/peticion";
    }
    
    
    
//    public String consultarTodasPeticionesAceptadas(Model m,  UsuarioDto u){
//        
//         m.addAttribute("peticionesAceptadas",servicePeticion.consultarPeticionesAceptadas(u));
//         return "redirect:/peticion/peticion";
//    }
//    public String consultarTodasPeticionesRechazadas(Model m, UsuarioDto u){
//         m.addAttribute("peticionesRechazadas",servicePeticion.consultarPeticionesPendientes(u));
//         return "redirect:/peticion/peticion";
//    }
}
