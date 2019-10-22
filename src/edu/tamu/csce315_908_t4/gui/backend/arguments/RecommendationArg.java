package edu.tamu.csce315_908_t4.gui.backend.arguments;

import edu.tamu.csce315_908_t4.gui.backend.Nconst;
import edu.tamu.csce315_908_t4.gui.backend.Tconst;

public class RecommendationArg implements IArgument{
    public final Nconst actorNconst;
    public final String genre;
    public final int year;

    public RecommendationArg(Nconst actorNconst, String genre, int year){
        this.actorNconst = actorNconst;
        this.genre = genre;
        this.year = year;
    }

}
