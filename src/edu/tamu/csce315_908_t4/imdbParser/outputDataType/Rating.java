package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Rating implements IOutputTable{
    public final String tconst;
    public final float averageRating;
    public final long numVotes;

    public Rating(String tconst, float averageRating, long numVotes){
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }
}
