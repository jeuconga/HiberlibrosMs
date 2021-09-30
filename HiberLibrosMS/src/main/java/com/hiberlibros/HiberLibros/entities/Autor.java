package com.hiberlibros.HiberLibros.entities;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Autor {


    private Integer idAutor;

    private String nombre;

    private String apellidos;

    private String biografia;
    
    private Boolean desactivado;


    private List<Libro> libros;

//    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)//si  se borra autor, se borra relacion de autor_libro
//    private List<AutorLibro> autorLibros;

//    @Override
//    public String toString() {
//        return ""+nombre + " " + apellidos;
//    }
}
