package com.hiberlibros.HiberLibros.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Isabel
 */
@Data  
@NoArgsConstructor
@AllArgsConstructor

public class Preferencia {

    private Integer id;

    private Genero genero;

    private Usuario usuario; 
}
