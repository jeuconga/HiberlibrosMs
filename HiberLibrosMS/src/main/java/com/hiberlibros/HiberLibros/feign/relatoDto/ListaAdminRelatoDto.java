/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign.relatoDto;

import com.hiberlibros.HiberLibros.dtos.RelatoDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaAdminRelatoDto {
    
        public List<RelatoAdminDto> relatos;
    
}
