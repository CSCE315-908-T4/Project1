package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Episode implements IOutputTable{
    public final String tconst;
    public final String parentTconst;
    public final Short seasonNumber;
    public final Integer episodeNumber;

    public Episode(String tconst, String parentTconst, Short seasonNumber, Integer episodeNumber){
        this.tconst = tconst;
        this.parentTconst = parentTconst;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }
}
