package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private final static String DB_PATH = "staffmanager.db";

    public Database(){
        final String url = "jdbc:sqlite:" + DB_PATH;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void close(){
        if(connection == null){

        }
    }
}

