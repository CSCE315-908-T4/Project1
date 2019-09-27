package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public enum OutputTable{
    AKA_TABLE("AKA"),
    CHARACTER_TABLE("Character"),
    CREW_TABLE("Crew"),
    EPISODE_TABLE("Episode"),
    KNOWN_FOR_TABLE("KnownFor"),
    PERSON_TABLE("Person"),
    PRINCIPAL_TABLE("Principal"),
    PROFESSION_TABLE("Profession"),
    RATING_TABLE("Rating"),
    TITLE_TABLE("Title"),
    GENRE_TABLE("Genre");

    public final String readableName;

    OutputTable(String readableName){
        this.readableName = readableName;
    }
}
