package com.hiberlibros.HiberLibros.dtos;

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
   
    private GeneroDto genero;
    
    private UsuarioDto usuario;
    
}
