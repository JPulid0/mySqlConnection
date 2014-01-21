package com.jpulido.dao;

import com.jpulido.model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jpulido.util.MySqlConnection;

public class ProfesorDao {

    public static boolean insert(Profesor profesor, Connection con) throws SQLException {

        boolean result;
        
        Connection connection = MySqlConnection.getConnection();
      
        PreparedStatement pst = connection.prepareStatement("INSERT INTO "
                + "TB_PROFESOR (idProfesor,usuario,nombres,apellidos,fechaNacimiento) "
                + "values (?,?,?,?,?)");

        pst.setInt(1, profesor.getIdProfesor());
        pst.setString(2, profesor.getUsuario());
        pst.setString(3, profesor.getNombres());
        pst.setString(4, profesor.getApellidos());
        pst.setDate(5, new java.sql.Date(profesor.getFechaNacimiento().getTime()));

        result = pst.executeUpdate() == 1;
        
        if (result) {
            System.out.println("Profesor registrado!!");
        } else {
            System.out.println("Profesor NO registrado!!");
        }

        return result;

    }

    public static List<Profesor> list(Connection con) throws SQLException {
               
        Profesor profesor;
        
        Connection connection; connection = MySqlConnection.getConnection();
        
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM TB_PROFESOR");

        ResultSet rs = pst.executeQuery();
        
        List<Profesor> listaProfesores  = new ArrayList<Profesor>();
        while (rs.next()) {

            profesor = new Profesor();
            profesor.setIdProfesor(rs.getInt(1));
            profesor.setUsuario(rs.getString(2));
            profesor.setNombres(rs.getString(3));
            profesor.setApellidos(rs.getString(4));
            profesor.setFechaNacimiento(new Date(rs.getDate(5).getTime()));

            listaProfesores.add(profesor);
        }

        return listaProfesores;
    }
}
