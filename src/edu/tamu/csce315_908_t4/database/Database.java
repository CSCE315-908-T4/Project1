package edu.tamu.csce315_908_t4.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    private static final String DATABASE_URL = "jdbc:postgresql://db-315.cse.tamu.edu/CSCE315-908-T4_MovieDB";
    private static final String DATABASE_USERNAME = "Buzzec";
    private static final String DATABASE_PASSWORD = "#E?Ez>wCg=x+-#sDn2xA*rq33";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
