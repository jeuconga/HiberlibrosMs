package com.hiberlibros.HiberLibros.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Isabel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Genero {

    private Integer id;

    private String nombre;
    private Boolean desactivado;


    private List<Relato> listaRelatos;


    private List<Libro> listaLibros;

    private List<Preferencia> listaPreferencias;

}
