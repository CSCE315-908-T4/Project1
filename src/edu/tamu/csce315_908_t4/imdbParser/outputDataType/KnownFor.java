package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class KnownFor implements IOutputTable{
    public final String nconst;
    public final String tconst;

    public KnownFor(String nconst, String tconst){
        this.nconst = nconst;
        this.tconst = tconst;
    }
}
