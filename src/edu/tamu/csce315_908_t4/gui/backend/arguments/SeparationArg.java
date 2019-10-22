package edu.tamu.csce315_908_t4.gui.backend.arguments;

import edu.tamu.csce315_908_t4.gui.backend.Nconst;

public class SeparationArg implements IArgument{
    public final Nconst initialNconst;
    public final Nconst targetNconst;
    private Nconst excludedActor;

    public SeparationArg(Nconst initialNconst, Nconst targetNconst){
        this.initialNconst = initialNconst;
        this.targetNconst = targetNconst;
        excludedActor = null;
    }

    public Nconst getExcludedActor(){
        return excludedActor;
    }

    public void setExcludedActor(Nconst actor) {
        excludedActor = actor;
    }
}
