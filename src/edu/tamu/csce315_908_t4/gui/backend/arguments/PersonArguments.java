package edu.tamu.csce315_908_t4.gui.backend.arguments;

import java.lang.reflect.Field;

public class PersonArguments implements Arguments{
    private StringArg name;
    private IntArg birthYear;
    private IntArg deathYear;
    private StringArg profession;

    public PersonArguments(){
        for(Field field : PersonArguments.class.getDeclaredFields()){
            try{
                field.set(this, null);
            } catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }
    }

    public StringArg getName(){
        return name;
    }

    public void setName(StringArg name){
        this.name = name;
    }

    public IntArg getBirthYear(){
        return birthYear;
    }

    public void setBirthYear(IntArg birthYear){
        this.birthYear = birthYear;
    }

    public IntArg getDeathYear(){
        return deathYear;
    }

    public void setDeathYear(IntArg deathYear){
        this.deathYear = deathYear;
    }

    public StringArg getProfession(){
        return profession;
    }

    public void setProfession(StringArg profession){
        this.profession = profession;
    }
}
