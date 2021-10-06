package com.hiberlibros.HiberLibros.feign.preferenciaDto;

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
    
    private List<Preferencia> preferencias;
    
    private Genero genero;
    
    private Preferencia formulario;
   
    //private Usuario usuario;   
}
