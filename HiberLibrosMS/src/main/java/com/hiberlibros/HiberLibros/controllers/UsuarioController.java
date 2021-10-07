package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.feign.UsuarioFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioFeign feignUsuario;

//    @GetMapping
//    public String usuarioFormulario(Model m, String registro) { //devuelve una lista con todos los usuarios, parte administrador
//        UsuarioFormularioDto ufd = feignUsuario.usuarioFormulario(registro);
//        m.addAttribute("registro", ufd.getRegistro());
//        m.addAttribute("usuarios", ufd.getUsuarios());
//        return "/usuarios/usuariosFormulario";
//    }

    @PostMapping("/guardarUsuario")//guarda un usuario devuelve un mensaje de error concreto
    public String usuarioRegistrar(UsuarioDto u, String password) {
        System.out.println(u.getNombre());
        String resultado = feignUsuario.usuarioRegistrar(u, password);
        if (resultado.contains("Error")) {
            return "redirect:/hiberlibros?error=" + resultado;//mail existente, mail no válido
        } else {

            return "redirect:/hiberlibros";

        }

    }

    @PostMapping("/editarUsuario")//edita usuario, manda el usuario para rellenar el formulario
    public String usuarioEditar(Usuario u) {
        feignUsuario.usuarioEditar(u);
        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/borrar")
    public String borrar(Integer id) {//borra usuario por ID en administrador
        String borrado = "";
        if (feignUsuario.borrar(id)) {
            borrado = "Usuario borrado";
        } else {
            borrado = "Error, no es posible borrar este usuario";
        }
        return "redirect:listarAdmin?borrado=" + borrado;
    }

    @GetMapping("/borrarUsuario")//borra usuario por ID en HIBERLIBRO
    public String borrarUsuario(Integer id) {
        String error = "";
        if (feignUsuario.borrar(id)) {
            error = "Usuario borrado";
        } else {
            error = "Error, no es posible borrar este usuario";
        }
        return "redirect:/hiberlibros?error=" + error;
    }

    @GetMapping("/listarAdmin")
    private String listarTodo(Model m, String borrado) {
        m.addAttribute("usuarios", feignUsuario.listarTodo());
        if (borrado != null) {
            m.addAttribute("borrado", borrado);
        }
        return "/administrador/usuarios";
    }

    @PostMapping("/altaAdmin")
    public String altaAdmin(Usuario u, String password) {
        String resultado = feignUsuario.altaAdmin(u, password);
        return "redirect:listarAdmin?borrado=" + resultado;
    }

    @PostMapping("/imagenPerfil")
    public String imagenPerfil(Model m, Integer id, MultipartFile ficheroImagen) {
        feignUsuario.imagenPerfil(ficheroImagen, id);
        return "redirect:/hiberlibros/panelUsuario";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> mostrarImagen(String imagen) {

        return feignUsuario.mostrarImagen(imagen);

    }

}
