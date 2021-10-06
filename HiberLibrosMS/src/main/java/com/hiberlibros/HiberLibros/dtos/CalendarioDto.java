/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import java.util.Date;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Mohamad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarioDto {

    private Integer id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String summary;
}
