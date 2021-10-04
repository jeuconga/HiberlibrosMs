/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.configuracion.FeignSupportConfig;
import com.hiberlibros.HiberLibros.dtos.RelatoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(contextId = "srelato", name = "HiberLibrosBack", configuration = FeignSupportConfig.class)
@RequestMapping("/hiberlibrosback")
public interface RelatoFeign {

    @PostMapping("/editarRelato")
    public void editarRelato(@SpringQueryMap RelatoDto relato);

    @GetMapping("/eliminarRelato")
    public String borrarRelato(@RequestParam Integer id);
}
