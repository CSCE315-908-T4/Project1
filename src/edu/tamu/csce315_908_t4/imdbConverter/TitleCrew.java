package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TitleCrew{
    public final String tconst;
    public final String directors;
    public final String writers;

    public TitleCrew(ResultSet resultSet) throws SQLException{
        this.tconst = resultSet.getString("tconst");
        this.directors = resultSet.getString("directors");
        this.writers = resultSet.getString("writers");
    }
}
