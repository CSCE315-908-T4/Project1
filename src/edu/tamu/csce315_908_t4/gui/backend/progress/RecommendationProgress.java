package edu.tamu.csce315_908_t4.gui.backend.progress;

import edu.tamu.csce315_908_t4.gui.backend.Nconst;

public class RecommendationProgress implements IProgress{
    public final int currentDepth;
    public final Nconst currentSearch;
    public final State state;

    public RecommendationProgress(int currentDepth, Nconst currentSearch, State state){
        this.currentDepth = currentDepth;
        this.currentSearch = currentSearch;
        this.state = state;
    }

    public enum State{
        START,
        DONE,
    }
}
