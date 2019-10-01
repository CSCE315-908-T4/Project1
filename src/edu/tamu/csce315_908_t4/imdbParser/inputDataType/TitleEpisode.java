package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleEpisode {
    private String tconst;
    private String parentTconst;
    private Short seasonNumber;
    private Integer episodeNumber;

    public TitleEpisode(String tconst, String parentTconst, Short seasonNumber, Integer episodeNumber){
        this.tconst = tconst;
        this.parentTconst = parentTconst;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public String getTconst(){
        return tconst;
    }

    public String getParentTconst(){
        return parentTconst;
    }

    public Short getSeasonNumber(){
        return seasonNumber;
    }

    public Integer getEpisodeNumber(){
        return episodeNumber;
    }
}
