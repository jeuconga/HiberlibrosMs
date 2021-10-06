/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import java.util.List;
import lombok.Data;

@Data
public class UsuarioSeguridadDtoFeign {
    private Integer id;
    private Integer idUsuario;
    private String mail;
    private String password;
    private List<RolDto> roles;
    
}
