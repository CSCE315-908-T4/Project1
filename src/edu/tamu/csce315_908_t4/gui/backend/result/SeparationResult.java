package edu.tamu.csce315_908_t4.gui.backend.result;

import edu.tamu.csce315_908_t4.gui.backend.Nconst;
import edu.tamu.csce315_908_t4.gui.backend.Tconst;
import edu.tamu.csce315_908_t4.gui.backend.arguments.SeparationArg;

import java.util.LinkedList;

public class SeparationResult{
    public final LinkedList<SubResult> subResults;
    public final SeparationArg separationArg;

    public SeparationResult(SeparationArg separationArg){
        subResults = new LinkedList<>();
        this.separationArg = separationArg;
    }

    public static class SubResult{
        public final Nconst childNconst;
        public final Nconst parentNconst;
        public final Tconst movieTconst;

        public SubResult(Nconst childNconst, Nconst parentNconst, Tconst movieTconst){
            this.childNconst = childNconst;
            this.parentNconst = parentNconst;
            this.movieTconst = movieTconst;
        }
    }
}
