package edu.tamu.csce315_908_t4.gui.backend.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TitleResult implements Result{
    private ArrayList<TitleItem> titleItems;

    public TitleResult(ResultSet resultSet) throws SQLException{
        titleItems = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            titleItems.add(new TitleItem(
                    resultSet.getString("titleType"),
                    resultSet.getString("primaryTitle"),
                    resultSet.getString("startYear"),
                    resultSet.getString("endYear"),
                    resultSet.getString("runtimeMinutes"),
                    resultSet.getString("isAdult"),
                    resultSet.getString("averageRating"),
                    resultSet.getString("numVotes")
            ));
        }
    }

    public static class TitleItem{
        public final String titleType;
        public final String primaryTitle;
        public final String startYear;
        public final String endYear;
        public final String runtimeMinutes;
        public final String isAdult;
        public final String averageRating;
        public final String numVotes;

        private TitleItem(String titleType, String primaryTitle, String startYear, String endYear, String runtimeMinutes, String isAdult, String averageRating, String numVotes){
            this.titleType = titleType;
            this.primaryTitle = primaryTitle;
            this.startYear = startYear;
            this.endYear = endYear;
            this.runtimeMinutes = runtimeMinutes;
            this.isAdult = isAdult;
            this.averageRating = averageRating;
            this.numVotes = numVotes;

        }
    }
}
