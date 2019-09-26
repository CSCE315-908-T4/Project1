package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

import java.util.ArrayList;

public class InputDataBlock{
    private ArrayList<NameBasic> nameBasics;
    private ArrayList<TitleAKA> titleAKAs;
    private ArrayList<TitleBasic> titleBasics;
    private ArrayList<TitleCrew> titleCrews;
    private ArrayList<TitleEpisode> titleEpisodes;
    private ArrayList<TitlePrincipal> titlePrincipals;
    private ArrayList<TitleRating> titleRatings;

    public static InputDataBlock readFromFiles(ArrayList<String> filePaths){
        return null;
    }

    public InputDataBlock(ArrayList<NameBasic> nameBasics, ArrayList<TitleAKA> titleAKAs, ArrayList<TitleBasic> titleBasics, ArrayList<TitleCrew> titleCrews, ArrayList<TitleEpisode> titleEpisodes, ArrayList<TitlePrincipal> titlePrincipals, ArrayList<TitleRating> titleRatings){
        this.nameBasics = nameBasics;
        this.titleAKAs = titleAKAs;
        this.titleBasics = titleBasics;
        this.titleCrews = titleCrews;
        this.titleEpisodes = titleEpisodes;
        this.titlePrincipals = titlePrincipals;
        this.titleRatings = titleRatings;
    }

    public ArrayList<TitleAKA> getTitleAKAs(){
        return titleAKAs;
    }

    public ArrayList<NameBasic> getNameBasics(){
        return nameBasics;
    }

    public ArrayList<TitleBasic> getTitleBasics(){
        return titleBasics;
    }

    public ArrayList<TitleCrew> getTitleCrews(){
        return titleCrews;
    }

    public ArrayList<TitleEpisode> getTitleEpisodes(){
        return titleEpisodes;
    }

    public ArrayList<TitlePrincipal> getTitlePrincipals(){
        return titlePrincipals;
    }

    public ArrayList<TitleRating> getTitleRatings(){
        return titleRatings;
    }
}
