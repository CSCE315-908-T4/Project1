package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Title implements IOutputTable{
    public final String tconst;
    public final String titleType;
    public final String primaryTitle;
    public final String originalTitle;
    public final boolean isAdult;
    public final short startYear;
    public final short endYear;
    public final int runtimeMinutes;

    public Title(String tconst, String titleType, String primaryTitle, String originalTitle, boolean isAdult, short startYear, short endYear, int runtimeMinutes){
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
