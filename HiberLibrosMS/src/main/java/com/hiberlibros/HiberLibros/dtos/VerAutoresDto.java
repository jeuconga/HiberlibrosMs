package com.hiberlibros.HiberLibros.dtos;

import java.util.List;

import com.hiberlibros.HiberLibros.entities.Autor;

import lombok.Data;

@Data
public class VerAutoresDto {
    private List<Autor> autores;
    private Autor autorForm;
}
