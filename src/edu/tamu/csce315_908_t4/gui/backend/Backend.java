package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.database.Database;

import java.sql.Connection;

public class Backend{
    private Connection conn;

    public Backend(){
        this(Database.getConnection());
    }

    public Backend(Connection conn){
        this.conn = conn;
    }
}
