package edu.tamu.csce315_908_t4.gui.backend;

public class IntArg{
    int value;
    Type type;

    public IntArg(int value, Type type){
        this.value = value;
        this.type = type;
    }

    public enum Type{
        EQUALS,
        NOT,
        MIN,
        MAX
    }
}
