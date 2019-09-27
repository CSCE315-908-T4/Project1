package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Character implements IOutputTable{
    public final String tconst;
    public final String nconst;
    public final String character;

    public Character(String tconst, String nconst, String character){
        this.tconst = tconst;
        this.nconst = nconst;
        this.character = character;
    }
}
