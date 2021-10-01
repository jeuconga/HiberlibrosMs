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
@AllArgsConstructor
@NoArgsConstructor
public class LibroParamDto {//había otro dto que no sé muy bien de que es

    private Integer id;
    private String isbn;
    private String titulo;
    private String idioma;
    private String uriPortada;
    private Double valoracionLibro;
    private Integer numeroValoraciones;
    private Boolean desactivado;
    private Integer id_genero;
    private Integer id_editorial;
    private Integer id_autor;
    


    
}
