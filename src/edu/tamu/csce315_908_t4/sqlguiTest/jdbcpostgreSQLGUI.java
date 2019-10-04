package edu.tamu.csce315_908_t4.sqlguiTest;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import java.sql.DriverManager;
/*
Robert lightfoot
CSCE 315
9-25-2019
 */
public class jdbcpostgreSQLGUI{
    public static void main(String[] args){
        //Building the connection
        Connection conn = genConnection();
        JOptionPane.showMessageDialog(null, "Opened database successfully");
        StringBuilder cus_lname = new StringBuilder();
        try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            String sqlStatement = "SELECT \"tconst\" FROM \"titleCrew\"";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            //OUTPUT
            JOptionPane.showMessageDialog(null, "Customer Last names from the Database.");
            //System.out.println("______________________________________");
            while(result.next()){
                //System.out.println(result.getString("cus_lname"));
                cus_lname.append(result.getString("tconst")).append("\n");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error accessing Database.");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, cus_lname.toString());
        //closing the connection
        try{
            conn.close();
            JOptionPane.showMessageDialog(null, "Connection Closed.");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }//end try catch
    }//end main

    static Connection genConnection(){
        Connection conn;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/CSCE315-908-T4_MovieDB",
                    dbSetup.user, dbSetup.pswd);
        } catch(Exception e){
            conn = null;
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }//end try catch
        return conn;
    }
}//end Class
