package com.isabel.examen;

import com.isabel.examen.domain.Equipo;
import com.isabel.examen.domain.Jugador;
import com.isabel.examen.util.Alertas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppController implements Initializable{

    public TextField tfEquipo, tfCategoria, tfPatrocinador, tfNombre, tfApellidos, tfDorsal, tfEquipoJugador;
    public Button btRegistroEquipo, btRegistroJugador, btModificarJugador, btEliminarEquipo;
    public ListView<Equipo> lvLista;
    public ListView<Jugador> lvListaJugadores;

    private Equipo equipoSeleccionado;
    private EquipoDAO equipoDAO;
    private Jugador jugadorSeleccionado;
    private JugadorDAO jugadorDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        equipoDAO = new EquipoDAO();
        jugadorDAO = new JugadorDAO();

        try {
            equipoDAO.conectarBD();
            jugadorDAO.conectarBD();

        } catch (ClassNotFoundException e) {
            Alertas.avisarError("Error al conectar");
        } catch (SQLException e) {
            Alertas.avisarError("Error al conectar");
        } catch (IOException e) {
            Alertas.avisarError("Error al conectar");
        }

        cargarLista();
    }



    public void cargarLista(){
        try{

            //Lista equipos
            lvLista.getItems().clear();

            List<Equipo> equipos = equipoDAO.listarEquipos();
            lvLista.setItems(FXCollections.observableList(equipos));

            //Lista Jugadores
            lvListaJugadores.getItems().clear();

            List<Jugador> jugadores = jugadorDAO.listarJugadores();
            lvListaJugadores.setItems(FXCollections.observableArrayList(jugadores));




        }catch (SQLException sqle){
            Alertas.avisarError("Error al cargar la lista");
        }
    }


    @FXML
    public void registrarEquipo(Event event) {

        try {

        String nombre = tfEquipo.getText();
        String patrocinador = tfPatrocinador.getText();
        String categoria = tfCategoria.getText();

        if (nombre == null || nombre.isBlank() || nombre.isEmpty()){
            Alertas.avisarError("El nombre del equipo es obligatorio");
            return;

        }

        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setPatrocinador(patrocinador);
        equipo.setCategoria(categoria);


            equipoDAO.insertarEquipo(equipo);

            cargarLista();
            limpiarCajas();

        } catch (SQLException throwables) {
            Alertas.avisarError("Error al insertar el nuevo equipo");
        }
    }

    @FXML
    public void registrarJugador(Event event){

        try {

        String nombre = tfNombre.getText();
        String apellidos = tfApellidos.getText();
        String dorsal = tfDorsal.getText();
        int idEquipo = Integer.parseInt(tfEquipoJugador.getText());


        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        jugador.setApellidos(apellidos);
        jugador.setDorsal(dorsal);
        jugador.setId_equipo(idEquipo);

        jugadorDAO.nuevoJugador(jugador);

        cargarLista();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    public void modificarJugador(Event event){
        String nombre =tfNombre.getText();
        String apellidos = tfApellidos.getText();
        int equipo = Integer.parseInt(tfEquipoJugador.getText());

        if (equipo == 0){
            Alertas.avisarError("Selecciona un equipo");
            return;
        }

        Jugador jugador = new Jugador();

        try {
            jugadorDAO.modificarJugador(equipo, jugador);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        limpiarCajas();
    }

    @FXML
    public void eliminarEquipo(Event event){

        try {
            Equipo equipo = equipoSeleccionado;

            if (equipo == null){
            Alertas.avisarError("Selecciona un equipo de la lista");
            }

            //Borro el equipo
                equipoDAO.eliminarEquipo(equipo);

            //Cargo la lista ya modificada
            cargarLista();

        } catch (SQLException sqle) {
                Alertas.avisarError("No se ha podido eliminar el equipo");
        }
    }


    //Seleccionar equipo de la lista
    @FXML
    public void seleccionarEquipo(Event event){
            equipoSeleccionado = (Equipo) lvLista.getSelectionModel().getSelectedItem();

            cargarEquipo(equipoSeleccionado);
    }


    //Cargar equipo seleccionado en las cajas textFields
    private void cargarEquipo(Equipo equipo) {
        tfEquipo.setText(equipo.getNombre());
        tfPatrocinador.setText(equipo.getPatrocinador());
        tfCategoria.setText(equipo.getCategoria());
    }

    private void limpiarCajas(){
        tfEquipo.setText("");
        tfPatrocinador.setText("");
        tfCategoria.setText("");
    }

    public void cargarJugador(Jugador jugador){
        tfNombre.setText(jugador.getNombre());
        tfApellidos.setText(jugador.getApellidos());
        tfDorsal.setText(jugador.getDorsal());
        tfEquipoJugador.setText(String.valueOf(jugador.getId_equipo()));
    }

    @FXML
    public void seleccionarJugador(Event event){
        jugadorSeleccionado = lvListaJugadores.getSelectionModel().getSelectedItem();

        cargarJugador(jugadorSeleccionado);
    }
}
