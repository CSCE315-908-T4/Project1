package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class Character {
    private String tconst;
    private String nconst;
    private String character;

    public Character(String tconst, String nconst, String character){
        this.tconst = tconst;
        this.nconst = nconst;
        this.character = character;
    }

    public String getTconst(){
        return tconst;
    }

    public String getNconst(){
        return nconst;
    }

    public String getCharacter(){
        return character;
    }
}
