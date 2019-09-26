package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleCrew {
    private String tconst;
    private String directors;
    private String writers;

    public TitleCrew(String tconst, String directors, String writers){
        this.tconst = tconst;
        this.directors = directors;
        this.writers = writers;
    }

    public String getTconst(){
        return tconst;
    }

    public String getDirectors(){
        return directors;
    }

    public String getWriters(){
        return writers;
    }
}
