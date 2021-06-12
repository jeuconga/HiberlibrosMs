package com.isabel.examen;

import com.isabel.examen.domain.Equipo;
import com.isabel.examen.util.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EquipoDAO {

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


    public List<Equipo> listarEquipos() throws SQLException {

        List<Equipo> equipos = new ArrayList<>();

        String sql = "SELECT id, nombre, patrocinador FROM equipos";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        ResultSet resultado = sentencia.executeQuery();

        while(resultado.next()){
            Equipo equipo = new Equipo();

            equipo.setId(resultado.getInt(1));
            equipo.setNombre(resultado.getString(2));
            equipo.setPatrocinador(resultado.getString(3));


            equipos.add(equipo);
        }
        return equipos;
    }

    public void insertarEquipo(Equipo equipo) throws SQLException {
        String sql = "INSERT INTO equipos (nombre, patrocinador, categoria) VALUES (?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, equipo.getNombre());
        sentencia.setString(2, equipo.getPatrocinador());
        sentencia.setString(3, equipo.getCategoria());

        sentencia.execute();
    }

    public void eliminarEquipo(Equipo equipo) throws SQLException {
        String sql = "DELETE FROM equipos WHERE nombre = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, equipo.getNombre());
        sentencia.execute();
    }
}


