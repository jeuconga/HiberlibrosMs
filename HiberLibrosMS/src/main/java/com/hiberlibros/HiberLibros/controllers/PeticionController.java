package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Peticion;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String peticion(Model m, Peticion p){
        if (p.getId()!=null){
            m.addAttribute("peticion", p);
        }
        m.addAttribute("peticiones",servicePeticion.consultaTodasPeticiones());
        return "/peticion/peticion";
    }
    
    @GetMapping(value = "/alta") //Recibe los integer y crea una nueva petición, vuelve al panel de usuario
    public String peticionAlta(Model m, Integer id_ul){
        Usuario u = uService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        Peticion p=new Peticion();
        servicePeticion.insertaPeticion(p, id_ul, u); 
        return "redirect:/hiberlibros/panelUsuario";
    }
    
    @PostMapping(value = "/baja")
    public String peticionBaja(Model m, Peticion p){
        servicePeticion.eliminaPeticion(p);
        return "redirect:/peticion/peticion";
    }
    @GetMapping("/baja") //retira una solicitud solo con el ID de la petición para no tener que mandar un objeto petición
    public String retirarSolicitud(Integer id){
        servicePeticion.eliminarId(id);
        return "redirect:/hiberlibros/panelUsuario";//vuelve al panel
    }
    @PostMapping(value = "/modificacion")
    public String peticionModificacion(Model m, Peticion p){
        servicePeticion.insertaModificaPeticion(p);
        return "redirect:/peticion/peticion";
    }
    
    @PostMapping(value = "/aceptar")
    public String aceptarPeticion(Model m, Peticion p, Usuario u){
        servicePeticion.aceptarPeticion(p);
        return "redirect:/peticion/peticion";
    }
            
    @PostMapping(value = "/rechazar")
    public String rechazarPeticion(Integer id, Usuario u){
        return "redirect:/peticion/peticion";
    }
    
    @PostMapping(value = "/peticionesPendientes")
    
    public String consultarTodasPeticionesPendientes(Usuario u){
         feingPeticion.consultarTodasPeticionesPendientes(u);
        
//         m.addAttribute("peticionesPendientes",servicePeticion.consultarPeticionesPendientes(u));
         return "redirect:/peticion/peticion";
    }
    
    public String consultarTodasPeticionesAceptadas(Model m,  Usuario u){
        
         m.addAttribute("peticionesAceptadas",servicePeticion.consultarPeticionesAceptadas(u));
         return "redirect:/peticion/peticion";
    }
    public String consultarTodasPeticionesRechazadas(Model m, Usuario u){
         m.addAttribute("peticionesRechazadas",servicePeticion.consultarPeticionesPendientes(u));
         return "redirect:/peticion/peticion";
    }
}
