package com.hiberlibros.HiberLibros.entities;


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

public class ComentarioForo{

    private Integer id;
    
    private String  comentarioForo;

    private Usuario usuarioComentario;


    private ForoLibro foroLibro;
}
