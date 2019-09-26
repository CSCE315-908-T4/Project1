package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class Rating{
    private String tconst;
    private float averageRating;
    private long numVotes;

    public Rating(String tconst, float averageRating, long numVotes){
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getTconst(){
        return tconst;
    }

    public float getAverageRating(){
        return averageRating;
    }

    public long getNumVotes(){
        return numVotes;
    }
}
