package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.gui.backend.arguments.StringArg;
import edu.tamu.csce315_908_t4.gui.backend.result.CharacterResult;
import edu.tamu.csce315_908_t4.gui.backend.result.RecommendationResult;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IBackend{
    static IBackend getCurrent(){
        return new Backend();
    }

    Pair<String, Integer> findClosestName(String in);

    ArrayList<CharacterResult> getSeparation(SeparationArgs args);

    ArrayList<RecommendationResult> getRecommendations(RecommendationArgs recommendationArgs);

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


    class RecommendationArgs{
        public final String actor;
        public final String genre;
        public final int year;

        public RecommendationArgs(String actor, String genre, int year)
        {
            this.actor = actor;
            this.genre = genre;
            this.year = year;
        }

    }


    // for testing only
    static void main(String[] args){
        IBackend backend = IBackend.getCurrent();
        System.out.println(backend.findClosestName("John Birkin"));
    }
}
