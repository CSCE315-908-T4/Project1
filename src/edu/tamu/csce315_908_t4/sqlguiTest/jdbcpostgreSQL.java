package edu.tamu.csce315_908_t4.sqlguiTest;

import java.sql.Connection;
import java.sql.DriverManager;
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
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/sales",
                    dbSetup.user, dbSetup.pswd);
        } catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }//end try catch
        System.out.println("Opened database successfully");

        try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            String sqlStatement = "SELECT cus_lname FROM customer";
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
