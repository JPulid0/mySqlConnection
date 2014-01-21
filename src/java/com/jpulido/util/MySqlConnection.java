package com.jpulido.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnection {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    public static void load() {

        Properties config;

        try {

            config = ResourceLocator.findAsProperties("config.properties");

            url = config.getProperty("url");
            driver = config.getProperty("driver");
            user = config.getProperty("user");
            password = config.getProperty("pass");

            Class.forName(driver);

            System.out.println("Driver ha sido cargado..!!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 

    }

    public static Connection getConnection(){
                
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        
        return connection;
    }

}
