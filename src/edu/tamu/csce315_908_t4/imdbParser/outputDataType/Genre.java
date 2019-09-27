package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Genre implements IOutputTable{
    public final String tconst;
    public final String genre;

    public Genre(String tconst, String genre){
        this.tconst = tconst;
        this.genre = genre;
    }
}
