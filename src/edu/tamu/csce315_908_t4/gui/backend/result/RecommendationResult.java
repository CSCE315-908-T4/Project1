package edu.tamu.csce315_908_t4.gui.backend.result;

public class RecommendationResult {
    public final String actorName;
    public final String movieTitle;
    public final int year;

    public RecommendationResult(String actor, String movie, int year)
    {
        actorName = actor;
        movieTitle = movie;
        this.year = year;
    }
}
