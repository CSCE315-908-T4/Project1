package edu.tamu.csce315_908_t4.gui.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tconst{
    public final String value;

    public Tconst(String value){
        this.value = value;
    }

    public Tconst(ResultSet resultSet) throws SQLException{
        this.value = resultSet.getString("tconst");
    }

    public static ArrayList<Tconst> getTconsts(ResultSet resultSet) throws SQLException{
        ArrayList<Tconst> out = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            out.add(new Tconst(resultSet));
        }
        return out;
    }
}
