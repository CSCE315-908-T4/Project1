package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import edu.tamu.csce315_908_t4.imdbParser.inputDataType.*;

import java.util.ArrayList;

public class OutputDataBlock{
    private ArrayList<Aka> akas;
    private ArrayList<Character> characters;
    private ArrayList<Crew> crews;
    private ArrayList<Episode> episodes;
    private ArrayList<KnownFor> knownFor;
    private ArrayList<Person> people;
    private ArrayList<Principal> principals;
    private ArrayList<Profession> professions;
    private ArrayList<Rating> ratings;
    private ArrayList<Title> titles;
    private ArrayList<Genre> genres;

    public static void sendToFiles(OutputDataBlock outputDataBlock, ArrayList<String> filePaths){

    }

    public OutputDataBlock(InputDataBlock inputDataBlock){
        akas = new ArrayList<>(inputDataBlock.getTitleAKAs().size());
        characters = new ArrayList<>();
        crews = new ArrayList<>();
        episodes = new ArrayList<>();
        knownFor = new ArrayList<>();
        people = new ArrayList<>();
        principals = new ArrayList<>();
        professions = new ArrayList<>();
        ratings = new ArrayList<>();
        titles = new ArrayList<>();
        genres = new ArrayList<>();

        genAkas(inputDataBlock.getTitleAKAs());
        genCharacters(inputDataBlock.getTitlePrincipals());
        genCrews(inputDataBlock.getTitleCrews());
        genEpisodes(inputDataBlock.getTitleEpisodes());
        genKnownFors(inputDataBlock.getNameBasics());
        genPeople(inputDataBlock.getNameBasics());
        genPrinciples(inputDataBlock.getTitlePrincipals());
        genProfessions(inputDataBlock.getNameBasics());
        genRatings(inputDataBlock.getTitleRatings());
        genTitles(inputDataBlock.getTitleBasics());
        genGenres(inputDataBlock.getTitleBasics());
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

    public ArrayList<Person> getPeople(){
        return people;
    }

    public ArrayList<Principal> getPrincipals(){
        return principals;
    }

    public ArrayList<Profession> getProfessions(){
        return professions;
    }

    public ArrayList<Rating> getRatings(){
        return ratings;
    }

    public ArrayList<Title> getTitles(){
        return titles;
    }

    public ArrayList<Genre> getGenres(){
        return genres;
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

    private void genPeople(ArrayList<NameBasic> nameBasics){
        for(NameBasic nameBasic : nameBasics){
            people.add(new Person(
                    nameBasic.getNconst(),
                    nameBasic.getPrimaryName(),
                    nameBasic.getBirthYear(),
                    nameBasic.getDeathYear()
            ));
        }
    }

    private void genPrinciples(ArrayList<TitlePrincipal> titlePrincipals){
        for(TitlePrincipal titlePrincipal : titlePrincipals){
            principals.add(new Principal(
                    titlePrincipal.getTconst(),
                    titlePrincipal.getOrdering(),
                    titlePrincipal.getNconst(),
                    titlePrincipal.getCategory(),
                    titlePrincipal.getJob()
            ));
        }
    }

    private void genProfessions(ArrayList<NameBasic> nameBasics){
        for(NameBasic nameBasic : nameBasics){
            String[] primaryProfessions = nameBasic.getPrimaryProfession().split(",");
            for(String primaryProfession : primaryProfessions){
                professions.add(new Profession(
                        nameBasic.getNconst(),
                        primaryProfession
                ));
            }
        }
    }

    private void genRatings(ArrayList<TitleRating> titleRatings){
        for(TitleRating titleRating : titleRatings){
            ratings.add(new Rating(
                    titleRating.getTconst(),
                    titleRating.getAverageRating(),
                    titleRating.getNumVotes()
            ));
        }
    }

    private void genTitles(ArrayList<TitleBasic> titleBasics){
        for(TitleBasic titleBasic : titleBasics){
            titles.add(new Title(
                    titleBasic.getTconst(),
                    titleBasic.getTitleType(),
                    titleBasic.getPrimaryTitle(),
                    titleBasic.getOriginalTitle(),
                    titleBasic.isAdult(),
                    titleBasic.getStartYear(),
                    titleBasic.getEndYear(),
                    titleBasic.getRuntimeMinutes()
            ));
        }
    }

    private void genGenres(ArrayList<TitleBasic> titleBasics){
        for(TitleBasic titleBasic : titleBasics){
            String[] titleGenres = titleBasic.getGenres().split(",");
            for(String titleGenre : titleGenres){
                genres.add(new Genre(
                        titleBasic.getTconst(),
                        titleGenre
                ));
            }
        }
    }
}
