package edu.tamu.csce315_908_t4.gui.backend.arguments;

public class IntArg{
    public final int value;
    public final Type type;

    public IntArg(int value, Type type){
        this.value = value;
        this.type = type;
    }

    public enum Type{
        EQUALS("="),
        NOT("!="),
        MIN(">="),
        MAX("<=");

        public final String sql;

        Type(String sql){
            this.sql = sql;
        }
    }


}
