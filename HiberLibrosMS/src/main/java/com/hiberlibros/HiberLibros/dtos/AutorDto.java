package com.hiberlibros.HiberLibros.dtos;


import lombok.Data;

@Data
public class AutorDto {

    private Integer idAutor;
    private String nombre;
    private String apellidos;
    private String biografia;
    private Boolean desactivado;
}
