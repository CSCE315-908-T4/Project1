package edu.tamu.csce315_908_t4.gui.backend.result;

import edu.tamu.csce315_908_t4.gui.backend.Nconst;
import edu.tamu.csce315_908_t4.gui.backend.Tconst;
import edu.tamu.csce315_908_t4.gui.backend.arguments.RecommendationArg;

import java.util.LinkedList;

public class RecommendationResult {
    public final LinkedList<SubResult> subResults;
    public final RecommendationArg recommendationArg;

    public RecommendationResult(RecommendationArg recommendationArg){
        subResults = new LinkedList<>();
        this.recommendationArg = recommendationArg;
    }
    //class to separate each of the recommendations in the recommendation result
    public static class SubResult{
        public final Nconst actorNconst;
        public final Integer movieYear;
        public final Tconst movieTconst;

        public SubResult(Nconst actorNconst, int movieYear, Tconst movieTconst){
            this.actorNconst = actorNconst;
            this.movieYear = movieYear;
            this.movieTconst = movieTconst;
        }
    }
}
