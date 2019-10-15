package edu.tamu.csce315_908_t4.gui.backend.result;

public class RecommendationResult {
    public final String actorName;
    public final String movieTitle;
    public final int year;

    public RecommendationResult(String actorName, String movieTitle, int year){
        this.actorName = actorName;
        this.movieTitle = movieTitle;
        this.year = year;
    }
}
