package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.gui.backend.arguments.StringArg;
import edu.tamu.csce315_908_t4.gui.backend.result.CharacterResult;
import javafx.util.Pair;

import java.util.ArrayList;

public interface IBackend{
    static IBackend getCurrent(){
        return new Backend();
    }

    Pair<String, Integer> findClosestName(String in);

    ArrayList<CharacterResult> getSeparation(SeparationArgs args);

    class SeparationArgs{
        public final String initialActor;
        public final String targetActor;
        private final ArrayList<StringArg> excludedActors;

        public SeparationArgs(String initialActor, String targetActor){
            this.initialActor = initialActor;
            this.targetActor = targetActor;
            excludedActors = new ArrayList<>();
        }

        public ArrayList<StringArg> getExcludedActors(){
            return excludedActors;
        }

        public void addExcludedActor(StringArg actor){
            excludedActors.add(actor);
        }
    }
}
