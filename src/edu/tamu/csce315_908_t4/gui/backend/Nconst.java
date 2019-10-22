package edu.tamu.csce315_908_t4.gui.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Nconst{
    public final String value;

    public Nconst(String value){
        this.value = value;
    }

    public Nconst(ResultSet resultSet) throws SQLException{
        this.value = resultSet.getString("nconst");
    }

    public static ArrayList<Nconst> getNconsts(ResultSet resultSet) throws SQLException{
        ArrayList<Nconst> out = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            out.add(new Nconst(resultSet));
        }
        return out;
    }

    @Override
    public boolean equals(Object obj){
        if(getClass() == obj.getClass()){
            return value.equals(((Nconst) obj).value);
        }
        return false;
    }
}
