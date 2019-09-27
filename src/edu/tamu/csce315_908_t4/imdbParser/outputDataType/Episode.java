package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Episode implements IOutputTable{
    public final String tconst;
    public final String parentTconst;
    public final short seasonNumber;
    public final int episodeNumber;

    public Episode(String tconst, String parentTconst, short seasonNumber, int episodeNumber){
        this.tconst = tconst;
        this.parentTconst = parentTconst;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }
}
