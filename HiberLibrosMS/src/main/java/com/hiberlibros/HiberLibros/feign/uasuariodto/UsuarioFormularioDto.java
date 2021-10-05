/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign.uasuariodto;

import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import java.util.List;
import lombok.Data;

@Data
public class UsuarioFormularioDto {
    private String registro;
    private List<UsuarioDto> usuarios;
    
}
