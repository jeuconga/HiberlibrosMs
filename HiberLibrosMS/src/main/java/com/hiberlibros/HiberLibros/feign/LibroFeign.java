
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.dtos.LibroDto;
import com.hiberlibros.HiberLibros.dtos.LibroDtoMS;
import com.hiberlibros.HiberLibros.dtos.LibroParamDto;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.feign.inicioDto.MostrarFormularioLibrosDto;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "sLibro", name = "HiberLibrosBack")
@RequestMapping("/librosback")
public interface LibroFeign {
     @GetMapping("/libros")
    public MostrarFormularioLibrosDto  mostrarFormulario();
     @PostMapping("/guardar")
    public void guardarLIbro(@SpringQueryMap LibroParamDto libro);
//    @GetMapping("/eliminar")
//    public void eliminarLibro(@RequestParam Integer id);
//    @GetMapping("/modificar")
//    public Map<String,Object> modificarLibro(@RequestParam Integer id);
//    @GetMapping("/listarAdmin")
//    public Map<String, Object> listarTodo(@RequestParam String borrado);
//    @PostMapping("/guardarAdmin")
//    public void guardarAdmin(@SpringQueryMap Libro libro, @RequestParam Integer id_genero, @RequestParam Integer id_editorial,@RequestParam Integer id_autor);
//    @GetMapping("/eliminarAdmin")
//    public void eliminarAdmin(@RequestParam Integer id);
//    @PostMapping("/addValoracionLibro")
//    public void addValoracionLibro(@RequestParam Integer id, @RequestParam Integer valoracion);
}

