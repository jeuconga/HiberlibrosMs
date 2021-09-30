package com.hiberlibros.HiberLibros.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Usuario
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Intercambio {


    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPrestamo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date fechaDevolucion;
    

    private UsuarioLibro usuarioPrestador;

    private UsuarioLibro usuarioPrestatario;
    


}
