package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class NameBasic{
    private String nconst;
    private String primaryName;
    private short birthYear;
    private short deathYear;
    private String primaryProfession;
    private String knownForTitles;

    public NameBasic(String nconst, String primaryName, short birthYear, short deathYear, String primaryProfession, String knownForTitles){
        this.nconst = nconst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.primaryProfession = primaryProfession;
        this.knownForTitles = knownForTitles;
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

    public String getPrimaryProfession(){
        return primaryProfession;
    }

    public String getKnownForTitles(){
        return knownForTitles;
    }
}
