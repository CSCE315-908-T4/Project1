package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Person implements IOutputTable{
    public final String nconst;
    public final String primaryName;
    public final Short birthYear;
    public final Short deathYear;

    public Person(String nconst, String primaryName, Short birthYear, Short deathYear){
        this.nconst = nconst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
}