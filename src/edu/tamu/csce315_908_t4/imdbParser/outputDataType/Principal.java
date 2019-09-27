package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Principal implements IOutputTable{
    public final String tconst;
    public final int ordering;
    public final String nconst;
    public final String category;
    public final String job;

    public Principal(String tconst, int ordering, String nconst, String category, String job){
        this.tconst = tconst;
        this.ordering = ordering;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
    }
}
