package com.hiberlibros.HiberLibros.feign.preferenciaDto;

import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.dtos.PreferenciaDto;
import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Preferencia;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerPreferenciasDto {
    
    private List<PreferenciaDto> preferencias; 
    private List<GeneroDto> generos;  
    private Preferencia formulario;   
}
