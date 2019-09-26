package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class Aka{
    private String tconst;
    private int ordering;
    private String title;
    private String region;
    private String language;
    private boolean isOriginalTitle;

    public Aka(String tconst, int ordering, String title, String region, String language, boolean isOriginalTitle){
        this.tconst = tconst;
        this.ordering = ordering;
        this.title = title;
        this.region = region;
        this.language = language;
        this.isOriginalTitle = isOriginalTitle;
    }

    public String getTconst(){
        return tconst;
    }

    public int getOrdering(){
        return ordering;
    }

    public String getTitle(){
        return title;
    }

    public String getRegion(){
        return region;
    }

    public String getLanguage(){
        return language;
    }

    public boolean isOriginalTitle(){
        return isOriginalTitle;
    }
}
