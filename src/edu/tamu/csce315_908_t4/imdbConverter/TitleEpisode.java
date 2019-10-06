package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseInt;
import static edu.tamu.csce315_908_t4.imdbConverter.Util.sqlParseShort;

public class TitleEpisode{
    public final String tconst;
    public final String parentTconst;
    public final Short seasonNumber;
    public final Integer episodeNumber;

    public TitleEpisode(ResultSet resultSet) throws SQLException{
        this.tconst = resultSet.getString("tconst");
        this.parentTconst = resultSet.getString("parentTconst");
        this.seasonNumber = sqlParseShort(resultSet, "seasonNumber");
        this.episodeNumber = sqlParseInt(resultSet, "episodeNumber");
    }
}
