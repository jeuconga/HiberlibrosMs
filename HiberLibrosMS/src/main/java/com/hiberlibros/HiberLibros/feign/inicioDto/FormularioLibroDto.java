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

@Data
public class FormularioLibroDto {
    private List<AutorDto> autores;
    private List<GeneroDto> generos;
    private List<EditorialDto> editoriales;
    private List<LibroDtoMS> libros;
    private String noLibros;
}
