package com.isabel.examen;

import com.isabel.examen.domain.Jugador;
import com.isabel.examen.util.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JugadorDAO {

    private Connection conexion;

    public void conectarBD() throws ClassNotFoundException, SQLException, IOException {
        Properties configuracion = new Properties();
        configuracion.load(R.getProperties("database.properties"));
        String host = configuracion.getProperty("host");
        String port = configuracion.getProperty("port");
        String nombreBD = configuracion.getProperty("nombreBD");
        String username = configuracion.getProperty("username");
        String password = configuracion.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + nombreBD + "?serverTimezone=UTC", username, password);
    }

    public void desconectarBD() throws SQLException {
        conexion.close();
    }


    //INSERTAR JUGADOR
    public void nuevoJugador(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO jugadores (nombre, apellidos, dorsal, id_equipo) VALUES (?,?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, jugador.getNombre());
        sentencia.setString(2, jugador.getApellidos());
        sentencia.setString(3, jugador.getDorsal());
        sentencia.setInt(4, jugador.getId_equipo());

        sentencia.execute();
    }


    //LISTA JUGADORES
    public List<Jugador> listarJugadores() throws SQLException {

        List<Jugador> jugadores = new ArrayList<>();

        String sql = "SELECT nombre, apellidos, dorsal FROM jugadores";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()){
            Jugador jugador = new Jugador();

            jugador.setNombre(resultado.getString(1));
            jugador.setApellidos(resultado.getString(2));
            jugador.setDorsal(resultado.getString(3));

            jugadores.add(jugador);
        }
        return jugadores;
    }


    //MODIFICAR JUGADOR. CAMBIAR A OTRO EQUIPO
    public void modificarJugador(int id_equipo, Jugador jugador) throws SQLException{
        String sql = "UPDATE jugadores SET id_equipo = ? WHERE nombre = ? AND apellidos = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setInt(1, id_equipo);
        sentencia.setString(2, jugador.getNombre());
        sentencia.setString(3, jugador.getApellidos());

        sentencia.executeUpdate();
    }
}
