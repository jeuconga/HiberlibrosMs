package com.hiberlibros.HiberLibros.dtos;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PreferenciaDto {
    
    private Integer id;
   
    private Genero genero;
    
    private Usuario usuario;
    
}
