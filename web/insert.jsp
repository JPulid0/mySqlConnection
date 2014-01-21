
<%@page import="java.util.Date"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jpulido.dao.ProfesorDao"%>
<%@page import="com.jpulido.model.Profesor"%>
<%@page import="com.jpulido.util.MySqlConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
 
    // Cargar parametros de conexión
    MySqlConnection.load();
    
    // Recupera Conexión
    Connection con = MySqlConnection.getConnection();
    
    // Profesor
    Profesor profesor = new Profesor();
    profesor.setIdProfesor(3);
    profesor.setUsuario("cruiz");
    profesor.setNombres("Carla");
    profesor.setApellidos("Ruiz");   
    profesor.setFechaNacimiento(new Date());   
    
    try{        
        
        if(ProfesorDao.insert(profesor, con)){
            out.print("<h1> Se registró un profesor! </h1>");
        }        
        
    }catch (SQLException e){
        out.print("<h1> Error: " +e.getMessage() + " </h1>");
    }
    
%>