/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(contextId = "srelato", name = "HiberLibrosBack")
@RequestMapping("/hiberlibrosback")
public interface RelatoFeign {
    
    
    @GetMapping("/editarRelato")
    @ResponseBody
    public void editarRelato();
    
    
}
