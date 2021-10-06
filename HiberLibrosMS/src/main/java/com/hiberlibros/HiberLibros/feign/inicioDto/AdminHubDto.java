/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign.inicioDto;

import com.hiberlibros.HiberLibros.dtos.CalendarioDto;
import com.hiberlibros.HiberLibros.dtos.EventoDTO;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Mohamad
 */
@Data
public class AdminHubDto {
    private Integer numUsuarios;
    private Integer numLibros;
    private List<CalendarioDto> eventos;
}
