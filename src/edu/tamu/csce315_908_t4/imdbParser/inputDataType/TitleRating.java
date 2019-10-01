package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleRating{
    private String tconst;
    private Float averageRating;
    private Integer numVotes;

    public TitleRating(String tconst, Float averageRating, Integer numVotes){
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getTconst(){
        return tconst;
    }

    public Float getAverageRating(){
        return averageRating;
    }

    public Integer getNumVotes(){
        return numVotes;
    }
}
