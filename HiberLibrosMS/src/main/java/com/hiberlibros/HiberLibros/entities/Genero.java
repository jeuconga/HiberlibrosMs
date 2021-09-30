package com.hiberlibros.HiberLibros.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Isabel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Boolean desactivado;

    @OneToMany(mappedBy = "id")
    private List<Relato> listaRelatos;

    @OneToMany(mappedBy = "id")
    private List<Libro> listaLibros;

    @OneToMany(mappedBy = "id")
    private List<Preferencia> listaPreferencias;

}
