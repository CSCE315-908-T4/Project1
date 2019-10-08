package edu.tamu.csce315_908_t4.gui.backend.arguments;

import java.lang.reflect.Field;

public class EpisodeArguments implements Arguments{
    private IntArg numVotes;
    private StringArg title;
    private StringArg type;
    private Boolean isAdult;
    private IntArg startYear;
    private IntArg endYear;
    private IntArg runtime;
    private IntArg seasonNumber;
    private IntArg episodeNumber;
    private StringArg seriesName;

    public EpisodeArguments(){
        for(Field field : EpisodeArguments.class.getDeclaredFields()){
            try{
                field.set(this, null);
            } catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }
    }

    public IntArg getNumVotes(){
        return numVotes;
    }

    public void setNumVotes(IntArg numVotes){
        this.numVotes = numVotes;
    }

    public StringArg getTitle(){
        return title;
    }

    public void setTitle(StringArg title){
        this.title = title;
    }

    public StringArg getType(){
        return type;
    }

    public void setType(StringArg type){
        this.type = type;
    }

    public Boolean getAdult(){
        return isAdult;
    }

    public void setAdult(Boolean adult){
        isAdult = adult;
    }

    public IntArg getStartYear(){
        return startYear;
    }

    public void setStartYear(IntArg startYear){
        this.startYear = startYear;
    }

    public IntArg getEndYear(){
        return endYear;
    }

    public void setEndYear(IntArg endYear){
        this.endYear = endYear;
    }

    public IntArg getRuntime(){
        return runtime;
    }

    public void setRuntime(IntArg runtime){
        this.runtime = runtime;
    }

    public IntArg getSeasonNumber(){
        return seasonNumber;
    }

    public void setSeasonNumber(IntArg seasonNumber){
        this.seasonNumber = seasonNumber;
    }

    public IntArg getEpisodeNumber(){
        return episodeNumber;
    }

    public void setEpisodeNumber(IntArg episodeNumber){
        this.episodeNumber = episodeNumber;
    }

    public StringArg getSeriesName(){
        return seriesName;
    }

    public void setSeriesName(StringArg seriesName){
        this.seriesName = seriesName;
    }
}
