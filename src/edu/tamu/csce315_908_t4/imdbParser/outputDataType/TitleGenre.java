package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class TitleGenre{
    private String tconst;
    private String genre;

    public TitleGenre(String tconst, String genre){
        this.tconst = tconst;
        this.genre = genre;
    }

    public String getTconst(){
        return tconst;
    }

    public String getGenre(){
        return genre;
    }
}
