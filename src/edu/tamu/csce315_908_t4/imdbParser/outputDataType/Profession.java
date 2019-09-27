package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

@SuppressWarnings("WeakerAccess")
public class Profession implements IOutputTable{
    public final String nconst;
    public final String profession;

    public Profession(String nconst, String profession){
        this.nconst = nconst;
        this.profession = profession;
    }
}
