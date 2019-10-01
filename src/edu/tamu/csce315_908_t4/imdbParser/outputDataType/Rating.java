package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Rating implements IOutputTable{
    public final String tconst;
    public final Float averageRating;
    public final Integer numVotes;

    public Rating(String tconst, Float averageRating, Integer numVotes){
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }
}
