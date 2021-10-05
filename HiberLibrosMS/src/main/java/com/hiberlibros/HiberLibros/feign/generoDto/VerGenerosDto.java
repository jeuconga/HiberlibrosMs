package com.hiberlibros.HiberLibros.feign.generoDto;

import com.hiberlibros.HiberLibros.entities.Genero;
import java.util.List;
import lombok.Data;

@Data
public class VerGenerosDto {
    private List<Genero> generos;
    private Genero generoForm;
}
