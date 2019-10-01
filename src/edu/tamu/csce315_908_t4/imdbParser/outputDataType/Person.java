package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Person implements IOutputTable{
    public final String nconst;
    public final String primaryName;
    public final short birthYear;
    public final short deathYear;

    public Person(String nconst, String primaryName, short birthYear, short deathYear){
        this.nconst = nconst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
}