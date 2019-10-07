package edu.tamu.csce315_908_t4.gui.backend.arguments;

public class PersonArguments implements Arguments{
    private StringArg name;
    private IntArg birthYear;
    private IntArg deathYear;
    private StringArg profession;
    private StringArg relatedTitle;

    public PersonArguments(){
        name = null;
        birthYear = null;
        deathYear = null;
        profession = null;
        relatedTitle = null;
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

    public StringArg getRelatedTitle(){
        return relatedTitle;
    }

    public void setRelatedTitle(StringArg relatedTitle){
        this.relatedTitle = relatedTitle;
    }
}
