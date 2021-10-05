/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign.relatoDto;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Usuario;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class RelatoParamDto {
    private Integer id;
    private String fichero;
    private String emailUsuario;
    private String titulo;
    private Double valoracionUsuarios;
    private Integer numeroValoraciones;
    private Integer idGenero;    
}
