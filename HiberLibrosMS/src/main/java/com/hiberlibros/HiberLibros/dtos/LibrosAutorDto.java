package com.hiberlibros.HiberLibros.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrosAutorDto{

    private List<LibroDtoMS> libros;
}
