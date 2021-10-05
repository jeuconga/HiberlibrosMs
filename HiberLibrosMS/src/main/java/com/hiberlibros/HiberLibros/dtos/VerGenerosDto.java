/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import com.hiberlibros.HiberLibros.entities.Genero;
import java.util.List;
import lombok.Data;

@Data
public class VerGenerosDto {
    private List<Genero> generos;
    private Genero generoForm;
}
