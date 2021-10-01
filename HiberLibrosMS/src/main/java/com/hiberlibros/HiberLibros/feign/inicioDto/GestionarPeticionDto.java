/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign.inicioDto;

import com.hiberlibros.HiberLibros.dtos.PeticionDto;
import com.hiberlibros.HiberLibros.dtos.UsuarioLibroDto;
import java.util.List;
import lombok.Data;

@Data
public class GestionarPeticionDto {
    private PeticionDto peticiones;
    private List<UsuarioLibroDto> librosSolicitante;
    
}
