package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Aka implements IOutputTable{
    public final String tconst;
    public final int ordering;
    public final String title;
    public final String region;
    public final String language;
    public final boolean isOriginalTitle;

    public Aka(String tconst, int ordering, String title, String region, String language, boolean isOriginalTitle){
        this.tconst = tconst;
        this.ordering = ordering;
        this.title = title;
        this.region = region;
        this.language = language;
        this.isOriginalTitle = isOriginalTitle;
    }
}
