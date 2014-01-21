package com.jpulido.dao;

import com.jpulido.model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jpulido.util.MysqlDBConn;

public class ProfesorDao {

    public static boolean insert(Profesor profesor, Connection con) throws SQLException {

        boolean result;
        Connection connection;
        PreparedStatement pst;
        
        connection = MysqlDBConn.getConnection();

        pst = connection.prepareStatement("INSERT INTO "
                + "TB_PROFESOR (usuario,nombres,apellidos,fechaNacimiento) "
                + "values (?,?,?,?)");

        pst.setString(1, profesor.getUsuario());
        pst.setString(2, profesor.getNombres());
        pst.setString(3, profesor.getApellidos());
        pst.setDate(4, new java.sql.Date(profesor.getFechaNacimiento().getTime()));

        result = pst.executeUpdate() == 1;
        if (result) {
            System.out.println("Profesor registrado!!");
        } else {
            System.out.println("Profesor NO registrado!!");
        }

        return result;

    }

    public static List<Profesor> list(Connection con) throws SQLException {
        
        List<Profesor> listaProfesores;
        Connection connection;
        PreparedStatement pst;
        Profesor profesor;
        
        connection = MysqlDBConn.getConnection();

        pst = connection.prepareStatement("SELECT * FROM TB_PROFESOR");

        ResultSet rs = pst.executeQuery();
        listaProfesores = new ArrayList<Profesor>();
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
