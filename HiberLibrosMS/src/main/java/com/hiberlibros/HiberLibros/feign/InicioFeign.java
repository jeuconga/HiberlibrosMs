/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.configuracion.FeignSupportConfig;
import com.hiberlibros.HiberLibros.dtos.AutorDto;
import com.hiberlibros.HiberLibros.dtos.TablaLibrosDto;
import com.hiberlibros.HiberLibros.dtos.UsuarioLibroDto;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import com.hiberlibros.HiberLibros.feign.inicioDto.GestionarPeticionDto;
import com.hiberlibros.HiberLibros.feign.inicioDto.RelatosInsertarDto;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "s1", name = "HiberLibrosBack", configuration = FeignSupportConfig.class )
@RequestMapping("/hiberlibrosback")
public interface InicioFeign {

    @GetMapping("/buscarLibro")//Muestra la lita de libros, todos o los buscados si está relleno el campo buscador
    public Map<String, Object> buscarLibro(@RequestParam Integer id, @RequestParam String buscador, @RequestParam String email);

    @GetMapping("/tablaBuscarLibro")//Muestra la lita de libros, todos o los buscados si está relleno el campo buscador
    public List<TablaLibrosDto> tablaBuscarLibro(@RequestParam String email);

    @GetMapping("/editarUsuario")
    public Usuario editar(@RequestParam String email);
    
    @GetMapping("/finIntercambio")
    public void finIntercambio(@RequestParam Integer id);
    
    @GetMapping("/rechazarIntercambio")
    public void rechazarIntercambio(@RequestParam Integer id);
    
    @PostMapping("/realizarIntercambio")
    public void realizarIntercambio(@RequestParam Integer id_peticion,@RequestParam Integer usuarioPrestatario);

    @GetMapping("/gestionarPeticion") 
    public GestionarPeticionDto gestionarPeticion(@RequestParam Integer id);
    
    @GetMapping("/relato")
    public RelatosInsertarDto insertarRelato(@RequestParam Integer id);
    
    @GetMapping("/borrarUL")
    public String borrarUsuLibro(@RequestParam Integer id);
    
    @PostMapping(value = "/guardarRelato", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void formularioRelato(@RequestParam Integer idUsuarioRelato, @SpringQueryMap Relato relato);
//    public void formularioRelato(@RequestParam Integer idUsuarioRelato, @SpringQueryMap Relato relato,@RequestPart(value = "ficherosubido") MultipartFile ficherosubido);

    @PostMapping("/registroLibro")
    public String registrarLibro(@SpringQueryMap UsuarioLibro ul,@SpringQueryMap Libro l,@RequestParam Integer id_genero,@RequestParam Integer id_editorial, @RequestParam Integer id_autor);
    
    @PostMapping("/saveAutor") 
    public void insertarAutor(@SpringQueryMap AutorDto autor);
    
    @PostMapping("/guardarLibro") 
    public String guardarLibro(@RequestParam Integer libro,@SpringQueryMap UsuarioLibroDto ul, @RequestParam String email);

}
