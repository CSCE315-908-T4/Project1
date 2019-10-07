package edu.tamu.csce315_908_t4.gui.backend;

import java.lang.reflect.Field;

public class TitleArguments{
    private IntArg numVotes;
    private StringArg title;
    private StringArg type;
    private Boolean isAdult;
    private IntArg startYear;
    private IntArg endYear;
    private IntArg runtime;
    private StringArg genre;

    public TitleArguments(){
        for(Field field : this.getClass().getDeclaredFields()){

        }
    }
}
