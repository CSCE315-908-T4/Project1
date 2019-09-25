package edu.tamu.csce315_908_t4.imdbParser.dataTypes;

public class Crew {
    private String tconst;
    private String nconst;
    private Type type;

    public enum Type {
        DIRECTOR, WRITER
    }
}
