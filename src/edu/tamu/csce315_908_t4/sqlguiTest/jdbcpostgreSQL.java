package edu.tamu.csce315_908_t4.sqlguiTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//import java.sql.DriverManager;
/*
Tim McGuire, adapted from Robert lightfoot
CSCE 315
9-27-2019
 */
public class jdbcpostgreSQL{
    public static void main(String[] args){
        //Building the connection
        Connection conn = jdbcpostgreSQLGUI.genConnection();
        System.out.println("Opened database successfully");

        try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            String sqlStatement = "SELECT \"tconst\" FROM \"titleCrew\" LIMIT 10";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            //OUTPUT
            System.out.println("Customer Last names from the Database.");
            System.out.println("______________________________________");
            while(result.next()){
                System.out.println(result.getString("cus_lname"));
            }
        } catch(Exception e){
            System.out.println("Error accessing Database.");
        }

        //closing the connection
        try{
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e){
            System.out.println("Connection NOT Closed.");
        }//end try catch
    }//end main
}//end Class
