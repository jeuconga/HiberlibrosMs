package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.CalendarioDto;
import com.hiberlibros.HiberLibros.dtos.EventoDTO;
import com.hiberlibros.HiberLibros.entities.Evento;
import com.hiberlibros.HiberLibros.feign.AdministradorFeign;
import com.hiberlibros.HiberLibros.feign.inicioDto.AdminHubDto;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import com.hiberlibros.HiberLibros.repositories.EventoRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Mohamad
 */
@Controller
@RequestMapping("/hiberlibros/paneladmin")
public class AdministradorControlller {


    @Autowired
    private IUsuarioService usuService;
    @Autowired
    private ILibroService libserv;
    @Autowired
    private EventoRepository evrepo;
    
    @Autowired
    private AdministradorFeign administradorFeign;

    @GetMapping
    public String adminHub(Model m) {
           AdminHubDto ahd = administradorFeign.adminHub();
           m.addAttribute("numUsuarios",ahd.getNumUsuarios());
           m.addAttribute("numLibros",ahd.getNumLibros());
           m.addAttribute("eventos", ahd.getEventos());
        return  "administrador/adminPanel";
    } 
    
    @GetMapping("/addEvent")
    public String formEvento(){
        return "administrador/eventoForm";
    }
   
    @PostMapping("/evento")
     public String addEvento(CalendarioDto e){

        administradorFeign.addEvento(e.getId(),e.getStartDate(),e.getEndDate(),e.getSummary());
        
        return "redirect:/hiberlibros/paneladmin";
    }
     @GetMapping("/deleteEvento")
    @ResponseBody
      public void eliminar(Integer id){
          administradorFeign.eliminar(id);
      }
    @GetMapping("/buscarEvento")
    @ResponseBody
    public List<EventoDTO> buscar(String search){
       return administradorFeign.buscar(search);
    }
    
    

    @GetMapping("/contacto")
    public String adminContacto(Model m) {
        return "administrador/contacto";
    }
}
