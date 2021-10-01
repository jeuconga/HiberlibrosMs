
package com.hiberlibros.HiberLibros.feign;

import com.hiberlibros.HiberLibros.entities.Libro;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "sLibro", name = "HiberLibrosBack")
@RequestMapping("/hiberlibrosback")
public interface LibroFeign {
     @GetMapping("/libros")
    public Map<String, Object>  mostrarFormulario();
     @PostMapping("/guardar")
    public void guardarLIbro(@SpringQueryMap Libro libro, @RequestParam Integer id_genero, @RequestParam Integer id_editorial, @RequestParam Integer id_autor);
    @GetMapping("/eliminar")
    public void eliminarLibro(@RequestParam Integer id);
    @GetMapping("/modificar")
    public void modificarLibro(@RequestParam Integer id);
    @GetMapping("/listarAdmin")
    public Map<String, Object> listarTodo(@RequestParam String borrado);

}

