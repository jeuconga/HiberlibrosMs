/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import java.util.Date;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntercambioDto {

    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPrestamo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDevolucion;

    @ManyToOne
    private UsuarioLibroDto usuarioPrestador;

    @ManyToOne
    private UsuarioLibroDto usuarioPrestatario;

}
