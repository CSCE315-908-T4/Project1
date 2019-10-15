package edu.tamu.csce315_908_t4.gui.backend.result;

import edu.tamu.csce315_908_t4.gui.backend.Nconst;
import edu.tamu.csce315_908_t4.gui.backend.Tconst;

import java.util.ArrayList;


public class ListResult {
    public ArrayList<ActorResult> result;

    public ListResult(ArrayList<ActorResult> result)
    {
        this.result = result;
    }
    class ActorResult{
        public Nconst actorNconst;
        public ArrayList<Tconst> movieTconsts;

        public ActorResult(Nconst actor, ArrayList<Tconst> movies)
        {
            actorNconst = actor;
            movieTconsts = movies;
        }

    }

}


