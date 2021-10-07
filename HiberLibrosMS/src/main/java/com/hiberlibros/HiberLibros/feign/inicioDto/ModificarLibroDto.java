/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign.inicioDto;

import com.hiberlibros.HiberLibros.dtos.AutorDto;
import com.hiberlibros.HiberLibros.dtos.EditorialDto;
import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.dtos.LibroDtoMS;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Mohamad
 */
@Data
public class ModificarLibroDto {
    private String imagen;
    private LibroDtoMS libro;
     private List<GeneroDto> generos;
     private List<EditorialDto> editoriales;
     private List<AutorDto> autores;
    
}