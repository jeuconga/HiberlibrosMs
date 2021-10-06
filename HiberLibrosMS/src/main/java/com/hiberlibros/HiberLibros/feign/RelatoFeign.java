package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.configuracion.FeignSupportConfig;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.feign.relatoDto.ListaRelatoDto;
import com.hiberlibros.HiberLibros.feign.relatoDto.ModificarRelatoDto;
import com.hiberlibros.HiberLibros.feign.relatoDto.RelatoParamDto;
import feign.Param;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "srelato", name = "HiberLibrosBack", configuration = FeignSupportConfig.class)
@RequestMapping("/relatoback")
public interface RelatoFeign {

    /*@GetMapping("/eliminarRelato")
    public String borrarRelato(@RequestParam Integer id);*/

    @GetMapping("/modificar")
    public ModificarRelatoDto modificarRelato(@RequestParam Integer id);

    @PostMapping("/modificarRelato")
    public String modificarRelato(@SpringQueryMap RelatoParamDto relato);

    @GetMapping("/listaRelatos")
    public ListaRelatoDto mostrarRelatos(@RequestParam String mail);
    
    @GetMapping("/eliminarRelato")
    public void eliminarRelato(@RequestParam Integer id);    
    
    @PostMapping("/addValoracion")
    public void addValoracion(@RequestParam Double valoracion, @RequestParam Integer id, @RequestParam Integer idUsuario);
    
    @GetMapping("/relato/{id}")
    public Optional<Relato> buscarPorID(@Param Integer id);

    
}
