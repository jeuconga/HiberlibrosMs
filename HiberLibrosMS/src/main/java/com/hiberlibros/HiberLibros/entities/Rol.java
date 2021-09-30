package com.hiberlibros.HiberLibros.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    private Integer id;

    private String nombre_rol;

    private UsuarioSeguridad idUsuario;
}
