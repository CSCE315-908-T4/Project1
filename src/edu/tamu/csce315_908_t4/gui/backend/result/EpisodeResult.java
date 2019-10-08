package edu.tamu.csce315_908_t4.gui.backend.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EpisodeResult implements Result{
    private ArrayList<EpisodeItem> episodeItems;

    public EpisodeResult(ResultSet resultSet) throws SQLException{
        episodeItems = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            episodeItems.add(new EpisodeItem(
                    resultSet.getString("titleType"),
                    resultSet.getString("primaryTitle"),
                    resultSet.getString("startYear"),
                    resultSet.getString("endYear"),
                    resultSet.getString("runtimeMinutes"),
                    resultSet.getString("isAdult"),
                    resultSet.getString("averageRating"),
                    resultSet.getString("numVotes"),
                    resultSet.getString("seasonnumber"),
                    resultSet.getString("episodenumber")
            ));
        }
    }

    public ArrayList<EpisodeItem> getEpisodeItems(){
        return episodeItems;
    }

    public static class EpisodeItem{
        public final String titleType;
        public final String primaryTitle;
        public final String startYear;
        public final String endYear;
        public final String runtimeMinutes;
        public final String isAdult;
        public final String averageRating;
        public final String numVotes;
        public final String seasonNumber;
        public final String episodeNumber;

        private EpisodeItem(String titleType, String primaryTitle, String startYear, String endYear, String runtimeMinutes, String isAdult, String averageRating, String numVotes, String seasonNumber, String episodeNumber){
            this.titleType = titleType;
            this.primaryTitle = primaryTitle;
            this.startYear = startYear;
            this.endYear = endYear;
            this.runtimeMinutes = runtimeMinutes;
            this.isAdult = isAdult;
            this.averageRating = averageRating;
            this.numVotes = numVotes;
            this.seasonNumber = seasonNumber;
            this.episodeNumber = episodeNumber;
        }
    }
}
