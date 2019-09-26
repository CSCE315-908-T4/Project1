package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleBasics {
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private short startYear;
    private short endYear;
    private int runtimeMinutes;
    private String genres;

    public TitleBasics(String tconst, String titleType, String primaryTitle, String originalTitle, boolean isAdult, short startYear, short endYear, int runtimeMinutes, String genres){
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
    }

    public String getTconst(){
        return tconst;
    }

    public String getTitleType(){
        return titleType;
    }

    public String getPrimaryTitle(){
        return primaryTitle;
    }

    public String getOriginalTitle(){
        return originalTitle;
    }

    public boolean isAdult(){
        return isAdult;
    }

    public short getStartYear(){
        return startYear;
    }

    public short getEndYear(){
        return endYear;
    }

    public int getRuntimeMinutes(){
        return runtimeMinutes;
    }

    public String getGenres(){
        return genres;
    }
}
