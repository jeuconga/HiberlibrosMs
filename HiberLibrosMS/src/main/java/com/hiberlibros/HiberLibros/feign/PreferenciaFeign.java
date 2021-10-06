package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.PreferenciaDto;
import com.hiberlibros.HiberLibros.feign.preferenciaDto.VerPreferenciasDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "spreferencia", name="HiberLibrosBack", url="http://localhost:8092")
@RequestMapping("/preferenciaback")
public interface PreferenciaFeign {
    
    @GetMapping
    public VerPreferenciasDto verPreferencias(@RequestParam String mail);
    
//    @PostMapping("/guardar")
//    public void anadirPreferencia(@SpringQueryMap PreferenciaDto preferencia);
     @PostMapping("/guardar")
    public void anadirPreferencia(@RequestParam Integer idGenero, @RequestParam String email);
    
     @GetMapping("/borrar")
    public void borrarPreferencia(@RequestParam Integer id);
    
    
    
}
