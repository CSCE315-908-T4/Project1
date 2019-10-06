package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseShort;

public class NameBasic{
    public final String nconst;
    public final String primaryName;
    public final Short birthYear;
    public final Short deathYear;
    public final String primaryProfession;
    public final String knownForTitles;

    public NameBasic(ResultSet resultSet) throws SQLException{
        this.nconst = resultSet.getString("nconst");
        this.primaryName = resultSet.getString("primaryName");
        this.birthYear = sqlParseShort(resultSet, "birthYear");
        this.deathYear = sqlParseShort(resultSet, "deathYear");
        this.primaryProfession = resultSet.getString("primaryProfession");
        this.knownForTitles = resultSet.getString("knownForTitles");
    }
}
