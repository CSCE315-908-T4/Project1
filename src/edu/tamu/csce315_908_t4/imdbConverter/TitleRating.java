package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseFloat;
import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseInt;

public class TitleRating{
    public final String tconst;
    public final Float averageRating;
    public final Integer numVotes;

    public TitleRating(ResultSet resultSet) throws SQLException{
        this.tconst = resultSet.getString("tconst");
        this.averageRating = sqlParseFloat(resultSet, "averageRating");
        this.numVotes = sqlParseInt(resultSet, "numVotes");
    }
}
