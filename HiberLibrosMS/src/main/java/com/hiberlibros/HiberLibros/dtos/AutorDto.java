/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import com.hiberlibros.HiberLibros.entities.Libro;
import java.util.List;
import lombok.Data;

@Data
public class AutorDto {
    private Integer idAutor;
    private String nombre;
    private String apellidos;
    private String biografia;
    private Boolean desactivado;

//    private List<LibroDtoMS> libros;
}
