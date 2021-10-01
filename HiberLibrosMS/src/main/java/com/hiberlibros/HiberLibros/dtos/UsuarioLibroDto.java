/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLibroDto {
    
    private Integer id;
    private String estadoConservacion;
    private String estadoPrestamo;
    private String quieroTengo;
    private Boolean desactivado;
    private UsuarioDto usuario;
    private LibroDtoMS libro;
    
}
