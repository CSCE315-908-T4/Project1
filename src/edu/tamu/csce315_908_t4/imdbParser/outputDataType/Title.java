package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Title implements IOutputTable{
    public final String tconst;
    public final String titleType;
    public final String primaryTitle;
    public final String originalTitle;
    public final Boolean isAdult;
    public final Short startYear;
    public final Short endYear;
    public final Integer runtimeMinutes;

    public Title(String tconst, String titleType, String primaryTitle, String originalTitle, Boolean isAdult, Short startYear, Short endYear, Integer runtimeMinutes){
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
    }
}
