package com.isabel.examen.domain;

public class Jugador {

    private int id;
    private String nombre;
    private String apellidos;
    private String dorsal;
    private int id_equipo;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "     Apellidos: " + apellidos + "    Dorsal: " + dorsal;
    }
}
