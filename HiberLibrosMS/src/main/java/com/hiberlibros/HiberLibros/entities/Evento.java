/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Evento {

    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date endDate;
    
    
    private String summary;
    
}
