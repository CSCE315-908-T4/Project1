package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseInt;

public class TitlePrincipal{
    public final String tconst;
    public final Integer ordering;
    public final String nconst;
    public final String category;
    public final String job;
    public final String characters;

    public TitlePrincipal(ResultSet resultSet) throws SQLException{
        this.tconst = resultSet.getString("tconst");
        this.ordering = sqlParseInt(resultSet, "ordering");
        this.nconst = resultSet.getString("nconst");
        this.category = resultSet.getString("category");
        this.job = resultSet.getString("job");
        this.characters = resultSet.getString("characters");
    }
}
