package com.hiberlibros.HiberLibros.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDto implements GrantedAuthority {

    private String nombre;
    
    public void setNombre_rol(String valor){
        nombre=valor;
    }
    
    public String getNombre_rol(){
        return nombre;
    }

    @Override
    public String getAuthority() {
        return nombre;
    }
}
