package edu.tamu.csce315_908_t4.gui.backend.result;

public class CharacterResult{
    public final String actorName;
    public final String characterName;
    public final String movieName;
    public final int movieYear;

    public CharacterResult(String actorName, String characterName, String movieName, int movieYear){
        this.actorName = actorName;
        this.characterName = characterName;
        this.movieName = movieName;
        this.movieYear = movieYear;
    }
}
