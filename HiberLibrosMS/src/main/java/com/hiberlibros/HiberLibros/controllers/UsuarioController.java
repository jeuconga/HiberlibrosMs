package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController { 

    @Autowired
    private IUsuarioService serviceUsuario;

//    @Autowired
//    private UsuarioSeguridad serviceUsuarioSeguridad;
    private ISeguridadService serviceUsuarioSeguridad;

    @Value("${carpetas.recursos.hiberlibros}")
    private String rutaBase;

    @GetMapping
    public String usuarioFormulario(Model m, String registro) { //devuelve una lista con todos los usuarios, parte administrador
        m.addAttribute("registro", registro);
        m.addAttribute("usuarios", serviceUsuario.usuariosList());
        return "/usuarios/usuariosFormulario";
    }

    @PostMapping("/guardarUsuario")//guarda un usuario devuelve un mensaje de error concreto
    public String usuarioRegistrar(Usuario u, String password) {
        //String resultado = service.guardarUsuario(u);     
        String resultado = serviceUsuario.guardarUsuarioYSeguridad(u, password);
        if (resultado.contains("Error")) {
            return "redirect:/hiberlibros?error=" + resultado;//mail existente, mail no válido
        } else {

            //return "redirect:/hiberlibros/panelUsuario?mail=" + u.getMail();
            return "redirect:/hiberlibros";

        }

    }

    @PostMapping("/editarUsuario")//edita usuario, manda el usuario para rellenar el formulario
    public String usuarioEditar(Usuario u) {
        return "redirect:/hiberlibros/panelUsuario?mail=" + serviceUsuario.editarUsuario(u);
    }

    @GetMapping("/borrar")
    public String borrar(Integer id) {//borra usuario por ID en administrador
        String borrado="";
        if (serviceUsuario.borrarUsuario(id)) {
            borrado = "Usuario borrado";
        } else {
            borrado = "Error, no es posible borrar este usuario";
        }
        return "redirect:listarAdmin?borrado="+borrado;
    }

    @GetMapping("/borrarUsuario")//borra usuario por ID en HIBERLIBRO
    public String borrarUsuario(Integer id) {
        serviceUsuario.borrarUsuario(id);
        return "redirect:/hiberlibros";
    }

    @GetMapping("/listarAdmin")
    private String listarTodo(Model m, String borrado) {
        m.addAttribute("usuarios", serviceUsuario.usuariosList());
        if (borrado != null) {
            m.addAttribute("borrado", borrado);
        }
        return "/administrador/usuarios";
    }

    @PostMapping("/altaAdmin")
    public String altaAdmin(Usuario u, String password) {
        String resultado = serviceUsuario.guardarUsuarioYSeguridadAdmin(u, password);
        return "redirect:listarAdmin";
    }

    @PostMapping("/imagenPerfil")
    public String imagenPerfil(Model m, Integer id, MultipartFile ficheroImagen) {
        String nombre = UUID.randomUUID().toString();
        String nombreFichero = ficheroImagen.getOriginalFilename().toLowerCase();
        String extension = nombreFichero.substring(nombreFichero.lastIndexOf("."));
        String subir = rutaBase + nombre + extension;
        File f = new File(subir);
        f.getParentFile().mkdirs();
        System.out.println("error" + subir);
        try {
            Files.copy(ficheroImagen.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Usuario user = serviceUsuario.usuarioId(id);
            user.setUriFoto(subir);

            serviceUsuario.editarUsuario(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> mostrarImagen(String imagen) {

        return serviceUsuario.visualizarImagen(imagen);

    }

}
