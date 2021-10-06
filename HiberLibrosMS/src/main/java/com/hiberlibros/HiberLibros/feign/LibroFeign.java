
package com.hiberlibros.HiberLibros.feign;


import com.hiberlibros.HiberLibros.dtos.LibroDtoMS;
import com.hiberlibros.HiberLibros.dtos.LibroParamDto;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.feign.inicioDto.ListarAdminDto;
import com.hiberlibros.HiberLibros.feign.inicioDto.ModificarLibroDto;
import com.hiberlibros.HiberLibros.feign.inicioDto.MostrarFormularioLibrosDto;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "sLibro", name = "HiberLibrosBack", url="http://localhost:8092")
@RequestMapping("/librosback")
public interface LibroFeign {
     @GetMapping("/libros")
    public MostrarFormularioLibrosDto  mostrarFormulario();
     @PostMapping("/guardar")
    public void guardarLIbro(@SpringQueryMap LibroParamDto libro);
    @GetMapping("/eliminar")
    public Boolean eliminarLibro(@RequestParam Integer id);
    @GetMapping("/modificar")
    public ModificarLibroDto modificarLibro(@RequestParam Integer id);
    @GetMapping("/listarAdmin")
    public ListarAdminDto listarTodo(@RequestParam String borrado);
    @PostMapping("/guardarAdmin")
    public void guardarAdmin(@SpringQueryMap LibroParamDto libro);
    @GetMapping("/eliminarAdmin")
    public Boolean eliminarAdmin(@RequestParam Integer id);
    @PostMapping("/addValoracionLibro")
    public void addValoracionLibro(@RequestParam Integer id, @RequestParam Integer valoracion);
}

