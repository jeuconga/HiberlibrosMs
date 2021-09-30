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
public class UsuarioLibro {

    private Integer id;

    private String estadoConservacion;
    private String estadoPrestamo;
    private String quieroTengo;
    private Boolean desactivado;

    private Usuario usuario;

    private Libro libro;

}
