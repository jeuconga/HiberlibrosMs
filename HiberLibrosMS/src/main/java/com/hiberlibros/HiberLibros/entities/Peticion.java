package com.hiberlibros.HiberLibros.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Peticion {

    private Integer id;
    

    private UsuarioLibro idUsuarioLibro;
    
    private Usuario idUsuarioSolicitante;
    private Boolean aceptacion;
    private Boolean pendienteTratar;
}
