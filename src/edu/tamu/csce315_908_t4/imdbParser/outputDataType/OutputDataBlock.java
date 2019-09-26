package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import edu.tamu.csce315_908_t4.imdbParser.inputDataType.InputDataBlock;
import edu.tamu.csce315_908_t4.imdbParser.inputDataType.TitleAKAs;

import java.util.ArrayList;

public class OutputDataBlock{
    private ArrayList<Aka> aka;
    private ArrayList<java.lang.Character> character;
    private ArrayList<Crew> crew;
    private ArrayList<Episodes> episode;
    private ArrayList<KnownFor> knownFor;
    private ArrayList<People> people;
    private ArrayList<Principal> principal;
    private ArrayList<Profession> profession;
    private ArrayList<Rating> rating;
    private ArrayList<TitleBasic> titleBasic;
    private ArrayList<TitleGenre> titleGenre;

    public OutputDataBlock(InputDataBlock inputDataBlock){
        aka = new ArrayList<>(inputDataBlock.getTitleAKAs().size());
        character = new ArrayList<>();
        crew = new ArrayList<>();
        episode = new ArrayList<>();
        knownFor = new ArrayList<>();
        people = new ArrayList<>();
        principal = new ArrayList<>();
        profession = new ArrayList<>();
        rating = new ArrayList<>();
        titleBasic = new ArrayList<>();
        titleGenre = new ArrayList<>();

        genAka(inputDataBlock.getTitleAKAs());
        genCharacter();

    }

    private void genAka(ArrayList<TitleAKAs> titleAKAs){
        for(TitleAKAs titleAKA : titleAKAs){
            aka.add(new Aka(
                    titleAKA.getTitleId(),
                    titleAKA.getOrdering(),
                    titleAKA.getTitle(),
                    titleAKA.getRegion(),
                    titleAKA.getLanguage(),
                    titleAKA.isOriginalTitle()
            ));
        }
    }

    private void genCharacter(){

    }
}
