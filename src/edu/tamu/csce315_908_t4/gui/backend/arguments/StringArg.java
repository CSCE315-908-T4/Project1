package edu.tamu.csce315_908_t4.gui.backend.arguments;

public class StringArg{
    public final String value;
    public final Type type;

    public StringArg(String value, Type type){
        this.value = value;
        this.type = type;
    }

    public enum Type{
        EQUALS("="),
        NOT("!=");

        public final String sql;

        Type(String sql){
            this.sql = sql;
        }
    }
}
