package com.hiberlibros.HiberLibros.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Libro {

    private Integer id;

    private String isbn;
    private String titulo;
    private String idioma;
    private String uriPortada;
    private Double valoracionLibro;
    private Integer numeroValoraciones;
    private Boolean desactivado;


    private Autor autor;

    private Editorial editorial;

    private Genero genero;

}
