package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public enum InputTable {
    NAME_BASIC_TABLE("NameBasic", "nameBasic", 'n'),
    TITLE_BASIC_TABLE("TitleBasic", "titleBasic", 'b'),
    TITLE_CREW_TABLE("Crew", "titleCrew", 'c'),
    TITLE_EPISODE_TABLE("Episode", "titleEpisode", 'e'),
    TITLE_PRINCIPAL_TABLE("Principal", "titlePrincipal", 'p'),
    TITLE_RATING_TABLE("Rating", "titleRating", 'r');

    public final String readableName;
    public final String tableName;
    public final char commandArgument;

    InputTable(String readableName, String tableName, char commandArgument){
        this.readableName = readableName;
        this.tableName = tableName;
        this.commandArgument = commandArgument;
    }
}
