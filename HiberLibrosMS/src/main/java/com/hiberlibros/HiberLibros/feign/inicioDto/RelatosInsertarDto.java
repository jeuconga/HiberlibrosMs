/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign.inicioDto;

import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.dtos.RelatoDto;
import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import java.util.List;
import lombok.Data;

@Data
public class RelatosInsertarDto {
    private List<GeneroDto> generos;
    private UsuarioDto usuario;
    private List<RelatoDto> relatos;
    
}
