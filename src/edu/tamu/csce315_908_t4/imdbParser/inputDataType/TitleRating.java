package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleRating{
    private String tconst;
    private float averageRating;
    private int numVotes;

    public TitleRating(String tconst, float averageRating, int numVotes){
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

    public int getNumVotes(){
        return numVotes;
    }
}
