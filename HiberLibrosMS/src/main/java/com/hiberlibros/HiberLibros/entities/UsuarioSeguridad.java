package com.hiberlibros.HiberLibros.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioSeguridad {


    private Integer id;

    private Integer idUsuario;

    private String mail;
    private String password;


    private List<Rol> roles;

}
