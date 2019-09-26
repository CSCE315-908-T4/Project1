package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class People{
    private String nconst;
    private String primaryName;
    private short birthYear;
    private short deathYear;

    public People(String nconst, String primaryName, short birthYear, short deathYear){
        this.nconst = nconst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getNconst(){
        return nconst;
    }

    public String getPrimaryName(){
        return primaryName;
    }

    public short getBirthYear(){
        return birthYear;
    }

    public short getDeathYear(){
        return deathYear;
    }
}
