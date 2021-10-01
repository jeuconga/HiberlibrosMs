/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeticionDto {  
    private Integer id;   
    private UsuarioLibroDto idUsuarioLibro;
    private UsuarioDto idUsuarioSolicitante;
    private Boolean aceptacion;
    private Boolean pendienteTratar;
    
}
