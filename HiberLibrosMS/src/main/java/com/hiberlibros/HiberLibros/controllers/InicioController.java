/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.TablaLibrosDto;
import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Peticion;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.interfaces.IAutorService;
import com.hiberlibros.HiberLibros.interfaces.IEditorialService;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;

import com.hiberlibros.HiberLibros.interfaces.IIntercambioService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.interfaces.IPeticionService;
import com.hiberlibros.HiberLibros.interfaces.IRelatoService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioLibroService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/hiberlibrosback")
public class InicioController {

    @Autowired
    private IUsuarioService usuService;
    @Autowired
    private IGeneroService serviceGen;
    @Autowired
    private IAutorService serviceAutor;
    @Autowired
    private IEditorialService editoService;
    @Autowired
    private ILibroService liService;
    @Autowired
    private IRelatoService serviceRelato;
    @Autowired
    private IUsuarioLibroService ulService;
    @Autowired
    private IPeticionService petiService;
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private IIntercambioService serviceInter;

    @Autowired
    private ISeguridadService serviceSeguridad;

    private final String RUTA_BASE = "c:\\zzzzSubirFicheros\\";

    @GetMapping
    public String inicio(Model m, String error) {
        if (error != null) {
            m.addAttribute("error", error);
        }

        return "/principal/login";
    }

    @GetMapping("/pruebaContexto")
    @ResponseBody
    public String pruebaContexto() {
        return serviceSeguridad.getMailFromContext();
    }

    @PostMapping("/loginentrar")
    public String inicio(Model m, String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        List<String> roles = auth.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList());
        for (String rol : roles) {
            if ("ROLE_Administrador".equals(rol)) {
                return "redirect:/hiberlibros/paneladmin";
            } else {
                if ("ROLE_Usuario".equals(rol)) {
                    return "redirect:/hiberlibros/panelUsuario";
                }
            }
        }
        String error = "Usuario no registrado";
        return "redirect:/hiberlibros?error=" + error;
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "/principal/logout";
    }

    @GetMapping("/panelUsuario") //entrada al panel principal de usuario, se pasan todos los elementos que se han de mostrar
    public String panelUsuario(Model m, String mail) {
        Usuario u = usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        List<UsuarioLibro> ul = ulService.buscarUsuario(u);
        m.addAttribute("relatos", serviceRelato.encontrarPorAutor(u));
        m.addAttribute("usuario", u);
        m.addAttribute("libros", ulService.buscarUsuariotiene(u));
        m.addAttribute("misPeticiones", petiService.consutarPeticionesUsuarioPendientes(u));
        m.addAttribute("petiRecibidas", petiService.consultarPeticonesRecibidas(u));
        m.addAttribute("intercambiosPropios", serviceInter.encontrarULPrestador(ul));
        m.addAttribute("intercambiosPeticiones", serviceInter.encontrarULPrestatario(ul));
        m.addAttribute("librosUsuario", ulService.contarLibrosPorUsuario(u));
        m.addAttribute("numIntercambioPendiente", serviceInter.contarIntercambiosPendientes(ul));

        return "principal/usuarioPanel";
    }

    @GetMapping("/guardarLibro") //Guarda libros en la base de datos. Primero guarda un libro, y posteriormente lo mete en la tabla Usuario Libros
    public String formularioLibro(Model m, String buscador) {
        List<Libro> libros = new ArrayList<>();
        Usuario u = usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        String noLibros = "";
        m.addAttribute("libro", new Libro());//Para el formulario        
        m.addAttribute("autores", serviceAutor.consultarAutores());//autores para el desplegable
        m.addAttribute("autor", new Autor());//autores para formulario
        m.addAttribute("generos", serviceGen.getGeneros());//géneros formulario
        m.addAttribute("editoriales", editoService.consultaTodas());//editoriales formulario
        m.addAttribute("buscador", buscador);//Elemento de la barra buscador
        if (buscador != null) {//si es distinto de nulo buscara el libro por isbn o título en la base de datos
            libros = liService.buscarLibro(buscador);
            if (libros.isEmpty()) {
                noLibros = "Ningun libro encontrado"; //si no existe devuelve un mensaje de error
            } else {
                noLibros = "encontrado";
                m.addAttribute("libros", libros); //si existe devuelve un arraylist con todas las coincidencias
            }
        }
        m.addAttribute("noLibros", noLibros);//devuelve el mensaje de error o de éxito
        return "principal/guardarLibro";
    }

    @PostMapping("/guardarLibro") //guarda un libro en el UsuarioLibro si ese libro existe previamente en la base de datos
    public String guardarLibro(Integer libro, UsuarioLibro ul) {
        Usuario u = usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        Libro l = liService.libroId(libro);
        if (l.getValoracionLibro() == null) {
            l.setValoracionLibro(new Double(0));
            l.setNumeroValoraciones(0);
        } else {
            l.setNumeroValoraciones(1);
        }

        ulService.guardar(ul, l, u);
        return "redirect:/hiberlibros/panelUsuario";
    }

    @PostMapping("/saveAutor")//Guarda un autor y vuelve a la página de registrar libro
    public String insertarAutor(Autor autor) {
        serviceAutor.guardarAutor(autor);
        return "redirect:/hiberlibros/guardarLibro?buscador=XXX";
    }

    @PostMapping("/registroLibro")//Guarda un libro nuevo y luego lo guarda en Usuario Libro
    public String registrarLibro(UsuarioLibro ul, Libro l, Integer id_genero, Integer id_editorial, Integer id_autor) {
        l.setGenero(serviceGen.encontrarPorId(id_genero));
        l.setEditorial(editoService.consultaPorIdEditorial(id_editorial));
        l.setAutor(serviceAutor.encontrarAutor(id_autor).get());
        l.setNumeroValoraciones(1);
        liService.guardarLibro(l);
        Usuario u = usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        ulService.guardar(ul, l, u);
        return "redirect:/hiberlibros/panelUsuario";//vuelve a la página inicial
    }

    @GetMapping("/buscarLibro")//Muestra la lita de libros, todos o los buscados si está relleno el campo buscador
    public Map<String, Object> buscarLibro(Integer id, String buscador, String email) {
        Usuario u = usuService.usuarioRegistrado(email);
        Map<String,Object> m=new HashMap<>();
        m.put("usuario", u);
        if (buscador == null) {
            m.put("libros", ulService.buscarDisponibles(u));
        } else {
            m.put("libros", ulService.buscarContiene(buscador, u.getId()));
        }
        return m;
    }

    @PostMapping("/guardarRelato")
    public String formularioRelato(Model m, Integer id, Relato relato, MultipartFile ficherosubido) {
        serviceRelato.guardarRelato(RUTA_BASE, relato, ficherosubido, id);
        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/relato")
    public String prueba(Model model, Integer id) {
        model.addAttribute("generos", serviceGen.getGeneros());
        model.addAttribute("relatos", serviceRelato.todos());
        model.addAttribute("usuario", usuService.usuarioId(id));
        return "principal/relato";
    }

    @GetMapping("/borrarUL")//borra un libro de UsuarioLibro sin eliminarlo de la tabla de Libros
    public String borrarUsuLibro(Model m,Integer id) {
        if (ulService.borrar(id)) {
            m.addAttribute("borrado", "Borrado con éxito");
        } else {
            m.addAttribute("borrado", "Error, no es posible borrar este autor");
        }
        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/gestionarPeticion")
    public String gestionarPeticion(Model m, Integer id) {
        Peticion p = petiService.consultarPeticionId(id);
        m.addAttribute("peticiones", p);
        m.addAttribute("librosSolicitante", ulService.buscarUsuarioDisponibilidad(p.getIdUsuarioSolicitante(), "Tengo", "Libre"));
        return "principal/formPeticion";
    }

    @PostMapping("/realizarIntercambio")
    public String realizarIntercambio(Integer id_peticion, Integer usuarioPrestatario) {
        Peticion p = petiService.consultarPeticionId(id_peticion);
        UsuarioLibro ulPrestatario = ulService.encontrarId(usuarioPrestatario);
        UsuarioLibro ulPrestador = p.getIdUsuarioLibro();
        serviceInter.guardarIntercambio(ulPrestatario, ulPrestador);
        petiService.aceptarPeticion(p);

        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/rechazarIntercambio")
    public String rechazarIntercambio(Integer id) {
        petiService.rechazarPeticion(id);
        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/finIntercambio")
    public String finIntercambio(Integer id) {
        serviceInter.finIntercambio(id);
        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/editarUsuario")
    @ResponseBody
    public Usuario editar() {
        return usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
    }

    @GetMapping("/tablaBuscarLibro")
    public List<TablaLibrosDto> tablaBuscarLibro(String email) {
        //Usuario u = usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        Usuario u = usuService.usuarioRegistrado(email);
        List<UsuarioLibro> ul = ulService.buscarDisponibles(u);
        List<TablaLibrosDto> tld = ul.stream().map(x -> new TablaLibrosDto(
                x.getId(),
                x.getLibro().getId(),
                x.getLibro().getUriPortada(),
                x.getLibro().getIsbn(),
                x.getLibro().getTitulo(),
                x.getLibro().getAutor().getNombre() + " " + x.getLibro().getAutor().getApellidos(),
                x.getLibro().getIdioma(),
                x.getLibro().getEditorial().getNombreEditorial(),
                x.getLibro().getValoracionLibro(),
                x.getEstadoConservacion(),
                x.getUsuario().getNombre()))
                .collect(Collectors.toList());

        return tld;
    }

}
