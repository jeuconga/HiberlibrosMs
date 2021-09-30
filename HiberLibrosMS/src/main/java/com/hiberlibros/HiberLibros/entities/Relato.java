package com.hiberlibros.HiberLibros.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Relato {

    private Integer id;

 
    private String fichero;

    private Usuario usuario;

    private String titulo;

    private Double valoracionUsuarios;

    private Integer numeroValoraciones;

    private Genero genero;

}
