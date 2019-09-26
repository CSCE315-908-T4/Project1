package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import edu.tamu.csce315_908_t4.imdbParser.inputDataType.*;

import java.util.ArrayList;

public class OutputDataBlock{
    private ArrayList<Aka> akas;
    private ArrayList<Character> characters;
    private ArrayList<Crew> crews;
    private ArrayList<Episode> episodes;
    private ArrayList<KnownFor> knownFor;
    private ArrayList<People> people;
    private ArrayList<Principal> principals;
    private ArrayList<Profession> profession;
    private ArrayList<Rating> rating;
    private ArrayList<TitleBasic> titleBasic;
    private ArrayList<TitleGenre> titleGenre;

    public OutputDataBlock(InputDataBlock inputDataBlock){
        akas = new ArrayList<>(inputDataBlock.getTitleAKAs().size());
        characters = new ArrayList<>();
        crews = new ArrayList<>();
        episodes = new ArrayList<>();
        knownFor = new ArrayList<>();
        people = new ArrayList<>();
        principals = new ArrayList<>();
        profession = new ArrayList<>();
        rating = new ArrayList<>();
        titleBasic = new ArrayList<>();
        titleGenre = new ArrayList<>();

        genAkas(inputDataBlock.getTitleAKAs());
        genCharacters(inputDataBlock.getTitlePrincipals());
        genCrews(inputDataBlock.getTitleCrews());
        genEpisodes(inputDataBlock.getTitleEpisodes());
    }

    public ArrayList<Aka> getAkas(){
        return akas;
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

    public ArrayList<Crew> getCrews(){
        return crews;
    }

    public ArrayList<Episode> getEpisodes(){
        return episodes;
    }

    public ArrayList<KnownFor> getKnownFor(){
        return knownFor;
    }

    public ArrayList<People> getPeople(){
        return people;
    }

    public ArrayList<Principal> getPrincipals(){
        return principals;
    }

    public ArrayList<Profession> getProfession(){
        return profession;
    }

    public ArrayList<Rating> getRating(){
        return rating;
    }

    public ArrayList<TitleBasic> getTitleBasic(){
        return titleBasic;
    }

    public ArrayList<TitleGenre> getTitleGenre(){
        return titleGenre;
    }

    private void genAkas(ArrayList<TitleAKA> titleAKAs){
        for(TitleAKA titleAKA : titleAKAs){
            akas.add(new Aka(
                    titleAKA.getTitleId(),
                    titleAKA.getOrdering(),
                    titleAKA.getTitle(),
                    titleAKA.getRegion(),
                    titleAKA.getLanguage(),
                    titleAKA.isOriginalTitle()
            ));
        }
    }

    private void genCharacters(ArrayList<TitlePrincipal> titlePrincipals){
        for(TitlePrincipal titlePrincipal : titlePrincipals){
            String[] charactersArray = titlePrincipal.getCharacters().split(",");
            for(String individualCharacter : charactersArray){
                characters.add(new Character(
                        titlePrincipal.getTconst(),
                        titlePrincipal.getNconst(),
                        individualCharacter
                ));
            }
        }
    }

    private void genCrews(ArrayList<TitleCrew> titleCrews){
        for(TitleCrew titleCrew : titleCrews){
            String[] directors = titleCrew.getDirectors().split(",");
            String[] writers = titleCrew.getWriters().split(",");
            for(String director : directors){
                crews.add(new Crew(
                        titleCrew.getTconst(),
                        director,
                        Crew.Type.DIRECTOR
                ));
            }
            for(String writer : writers){
                crews.add(new Crew(
                        titleCrew.getTconst(),
                        writer,
                        Crew.Type.WRITER
                ));
            }
        }
    }

    private void genEpisodes(ArrayList<TitleEpisode> titleEpisodes){
        for(TitleEpisode titleEpisode : titleEpisodes){
            episodes.add(new Episode(
                    titleEpisode.getTconst(),
                    titleEpisode.getParentTconst(),
                    titleEpisode.getSeasonNumber(),
                    titleEpisode.getEpisodeNumber()
            ));
        }
    }

    private void genKnownFors(ArrayList<NameBasic> nameBasics){
        for(NameBasic nameBasic : nameBasics){
            String[] knownForTitles = nameBasic.getKnownForTitles().split(",");
            for(String knownForTitle : knownForTitles){
                knownFor.add(new KnownFor(
                        nameBasic.getNconst(),
                        knownForTitle
                ));
            }
        }
    }
}
