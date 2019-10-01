package edu.tamu.csce315_908_t4.sqlGUI;

import java.sql.*;
import javax.swing.JOptionPane;
//import java.sql.DriverManager;
/*
Robert lightfoot
CSCE 315
9-25-2019
 */
public class jdbcpostgreSQLGUI {
  public static void main(String args[]) {
    dbSetup my = new dbSetup();
    //Building the connection
     Connection conn = null;
     try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/CSCE315-908-T4_MovieDB",
           my.user, my.pswd);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }//end try catch
     JOptionPane.showMessageDialog(null,"Opened database successfully");
     String tconst = "";
     try{
     //create a statement object
       Statement stmt = conn.createStatement();
       //create an SQL statement
       String sqlStatement = "SELECT tconst FROM titleBasics";
       //send statement to DBMS
       ResultSet result = stmt.executeQuery(sqlStatement);

       //OUTPUT
       JOptionPane.showMessageDialog(null,"Customer Last names from the Database.");
       //System.out.println("______________________________________");
       while (result.next()) {
         //System.out.println(result.getString("cus_lname"));
         tconst += result.getString("const")+"\n";
       }
   } catch (Exception e){
     JOptionPane.showMessageDialog(null,"Error accessing Database.");
   }
   JOptionPane.showMessageDialog(null,tconst);
    //closing the connection
    try {
      conn.close();
      JOptionPane.showMessageDialog(null,"Connection Closed.");
    } catch(Exception e) {
      JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class
