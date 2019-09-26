package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class Profession{
    private String nconst;
    private String profession;

    public Profession(String nconst, String profession){
        this.nconst = nconst;
        this.profession = profession;
    }

    public String getNconst(){
        return nconst;
    }

    public String getProfession(){
        return profession;
    }
}
