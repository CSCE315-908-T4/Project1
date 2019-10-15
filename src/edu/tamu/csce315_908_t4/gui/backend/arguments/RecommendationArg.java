package edu.tamu.csce315_908_t4.gui.backend.arguments;

public class RecommendationArg implements IArgument{
    public final String actor;
    public final String genre;
    public final int year;

    public RecommendationArg(String actor, String genre, int year){
        this.actor = actor;
        this.genre = genre;
        this.year = year;
    }
}
