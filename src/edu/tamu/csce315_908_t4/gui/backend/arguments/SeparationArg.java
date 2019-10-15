package edu.tamu.csce315_908_t4.gui.backend.arguments;

import edu.tamu.csce315_908_t4.gui.backend.Nconst;

import java.util.ArrayList;

public class SeparationArg implements IArgument{
    public final Nconst initialNconst;
    public final Nconst targetNconst;
    private final ArrayList<Nconst> excludedActors;

    public SeparationArg(Nconst initialNconst, Nconst targetNconst){
        this.initialNconst = initialNconst;
        this.targetNconst = targetNconst;
        excludedActors = new ArrayList<>();
    }

    public ArrayList<Nconst> getExcludedActors(){
        return excludedActors;
    }

    public void addExcludedActor(Nconst actor){
        excludedActors.add(actor);
    }
}
