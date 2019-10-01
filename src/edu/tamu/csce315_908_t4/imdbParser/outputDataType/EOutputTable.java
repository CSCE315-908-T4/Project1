package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public enum EOutputTable{
    CHARACTER_TABLE("Character", Character.class),
    CREW_TABLE("Crew", Crew.class),
    EPISODE_TABLE("Episode", Episode.class),
    KNOWN_FOR_TABLE("KnownFor", KnownFor.class),
    PERSON_TABLE("Person", Person.class),
    PRINCIPAL_TABLE("Principal", Principal.class),
    PROFESSION_TABLE("Profession", Profession.class),
    RATING_TABLE("Rating", Rating.class),
    TITLE_TABLE("Title", Title.class),
    GENRE_TABLE("Genre", Genre.class);

    public final String readableName;
    public final Class<? extends IOutputTable> tableClass;

    EOutputTable(String readableName, Class<? extends IOutputTable> tableClass){
        this.readableName = readableName;
        this.tableClass = tableClass;
    }
}
