package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Crew implements IOutputTable{
    public final String tconst;
    public final String nconst;
    public final String type;

    public Crew(String tconst, String nconst, String type){
        this.tconst = tconst;
        this.nconst = nconst;
        this.type = type;
    }
}
