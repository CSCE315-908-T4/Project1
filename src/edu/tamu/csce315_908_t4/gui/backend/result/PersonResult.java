package edu.tamu.csce315_908_t4.gui.backend.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonResult implements Result{
    private ArrayList<PersonItem> personItems;

    public PersonResult(ResultSet resultSet) throws SQLException{
        personItems = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            personItems.add(new PersonItem(
                    resultSet.getString("primaryName"),
                    resultSet.getString("birthYear"),
                    resultSet.getString("deathYear"),
                    resultSet.getString("profession")
            ));
        }
    }

    public ArrayList<PersonItem> getPersonItems(){
        return personItems;
    }

    public static class PersonItem{
        public final String primaryName;
        public final String birthYear;
        public final String deathYear;
        public final String profession;

        private PersonItem(String primaryName, String birthYear, String deathYear, String profession){
            this.primaryName = primaryName;
            this.birthYear = birthYear;
            this.deathYear = deathYear;
            this.profession = profession;
        }
    }
}
