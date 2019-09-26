package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitleEpisode {
    private String tconst;
    private String parentTconst;
    private short seasonNumber;
    private int episodeNumber;

    public TitleEpisode(String tconst, String parentTconst, short seasonNumber, int episodeNumber){
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

    public short getSeasonNumber(){
        return seasonNumber;
    }

    public int getEpisodeNumber(){
        return episodeNumber;
    }
}
