package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class KnownFor{
    private String nconst;
    private String tconst;

    public KnownFor(String nconst, String tconst){
        this.nconst = nconst;
        this.tconst = tconst;
    }

    public String getNconst(){
        return nconst;
    }

    public String getTconst(){
        return tconst;
    }
}
