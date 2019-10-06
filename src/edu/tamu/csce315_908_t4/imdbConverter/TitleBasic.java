package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseInt;
import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseShort;

public class TitleBasic{
    public final String tconst;
    public final String titleType;
    public final String primaryTitle;
    public final String originalTitle;
    public final Boolean isAdult;
    public final Short startYear;
    public final Short endYear;
    public final Integer runtimeMinutes;
    public final String genres;

    public TitleBasic(ResultSet resultSet) throws SQLException{
        this.tconst = resultSet.getString("tconst");
        this.titleType = resultSet.getString("titleType");
        this.primaryTitle = resultSet.getString("primaryTitle");
        this.originalTitle = resultSet.getString("originalTitle");
        this.isAdult = resultSet.getBoolean("isAdult");
        this.startYear = sqlParseShort(resultSet, "startYear");
        this.endYear = sqlParseShort(resultSet, "endYear");
        this.runtimeMinutes = sqlParseInt(resultSet, "runtimeMinutes");
        this.genres = resultSet.getString("genres");
    }
}
