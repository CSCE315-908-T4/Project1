package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleAKAs{
    private String titleID;
    private int ordering;
    private String title;
    private String region;
    private String language;
    private String types;
    private String attributes;
    private boolean isOriginalTitle;

    public TitleAKAs(String titleId, int ordering, String title, String region, String language, String types, String attributes, boolean isOriginalTitle){
        this.titleId = titleId;
        this.ordering = ordering;
        this.title = title;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        this.isOriginalTitle = isOriginalTitle;
    }

    public String getTitleId(){
        return titleId;
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

    public String getTypes(){
        return types;
    }

    public String getAttributes(){
        return attributes;
    }

    public boolean isOriginalTitle(){
        return isOriginalTitle;
    }
}
