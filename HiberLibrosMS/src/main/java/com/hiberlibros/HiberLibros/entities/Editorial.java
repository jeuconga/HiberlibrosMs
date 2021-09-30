package com.hiberlibros.HiberLibros.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Editorial {
    

    private Integer id;
    
    private String nombreEditorial;
    private Boolean desactivado;

}