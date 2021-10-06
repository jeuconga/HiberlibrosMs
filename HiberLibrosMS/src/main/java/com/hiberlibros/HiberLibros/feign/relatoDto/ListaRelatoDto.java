package com.hiberlibros.HiberLibros.feign.relatoDto;

import com.hiberlibros.HiberLibros.dtos.RelatoDto;
import com.hiberlibros.HiberLibros.dtos.UsuarioDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaRelatoDto {

   private List<RelatoDto> relatos;
   private UsuarioDto usuario;

}
