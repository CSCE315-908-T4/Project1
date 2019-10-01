package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleBasic{
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private Boolean isAdult;
    private Short startYear;
    private Short endYear;
    private Integer runtimeMinutes;
    private String genres;

    public TitleBasic(String tconst, String titleType, String primaryTitle, String originalTitle, Boolean isAdult, Short startYear, Short endYear, Integer runtimeMinutes, String genres){
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

    public Boolean isAdult(){
        return isAdult;
    }

    public Short getStartYear(){
        return startYear;
    }

    public Short getEndYear(){
        return endYear;
    }

    public Integer getRuntimeMinutes(){
        return runtimeMinutes;
    }

    public String getGenres(){
        return genres;
    }
}
