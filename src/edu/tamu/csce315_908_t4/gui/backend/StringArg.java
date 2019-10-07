package edu.tamu.csce315_908_t4.gui.backend;

public class StringArg{
    String value;
    Type type;

    public StringArg(String value, Type type){
        this.value = value;
        this.type = type;
    }

    public enum Type{
        EQUALS,
        NOT
    }
}
