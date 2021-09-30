package com.hiberlibros.HiberLibros.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Usuario
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ForoLibro {
    
 
    private Integer id; // id propio
    
    private Boolean desactivado; //desactivado / activado
    
    private String  tituloForo; // titulo del foro
    

    private Libro   idLibro;   // libro del que es el foro
    

    private Usuario usuarioCreador;  //id Usuario creador del hilo

    
    private List<ComentarioForo> comentarios; //usuario que genera el foro
    
}
