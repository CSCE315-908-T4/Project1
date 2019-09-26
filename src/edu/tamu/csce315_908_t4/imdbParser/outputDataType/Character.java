package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class Character {
    private String tconst;
    private String nconst;
    private String charcter;

    public Character(String tconst, String nconst, String charcter){
        this.tconst = tconst;
        this.nconst = nconst;
        this.charcter = charcter;
    }

    public String getTconst(){
        return tconst;
    }

    public String getNconst(){
        return nconst;
    }

    public String getCharcter(){
        return charcter;
    }
}
