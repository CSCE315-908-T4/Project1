package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public enum InputTable {
    NAME_BASICS_TABLE("NameBasics"),
    TITLE_AKA_TABLE("AKA"),
    TITLE_BASICS_TABLE("TitleBasics"),
    TITLE_CREW_TABLE("Crew"),
    TITLE_EPISODE_TABLE("Episode"),
    TITLE_PRINCIPAL_TABLE("Principal"),
    TITLE_RATING_TABLE("Rating");

    public final String readableName;

    InputTable(String readableName) {
        this.readableName = readableName;
    }
}
