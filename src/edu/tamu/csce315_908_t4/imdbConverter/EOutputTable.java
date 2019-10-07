package edu.tamu.csce315_908_t4.imdbConverter;

public enum EOutputTable{
    CHARACTER_TABLE("Character"),
    CREW_TABLE("Crew"),
    EPISODE_TABLE("Episode"),
    KNOWN_FOR_TABLE("Known For"),
    PERSON_TABLE("Person"),
    PRINCIPAL_TABLE("Principal"),
    PROFESSION_TABLE("Profession"),
    RATING_TABLE("Rating"),
    TITLE_TABLE("Title"),
    GENRE_TABLE("Genre");

    public final String tableName;

    EOutputTable(String tableName){
        this.tableName = tableName;
    }
}
