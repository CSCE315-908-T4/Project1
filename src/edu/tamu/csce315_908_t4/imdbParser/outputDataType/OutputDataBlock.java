package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import edu.tamu.csce315_908_t4.imdbParser.inputDataType.*;

import java.util.ArrayList;

/**
 * All data to be output in object form.
 */
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

    /**
     * Creates an {@link OutputDataBlock} form an {@link InputDataBlock}
     *
     * @param inputDataBlock The input data used to create output data
     */
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
        genPrincipals(inputDataBlock.getTitlePrincipals());
        genProfessions(inputDataBlock.getNameBasics());
        genRatings(inputDataBlock.getTitleRatings());
        genTitles(inputDataBlock.getTitleBasics());
        genGenres(inputDataBlock.getTitleBasics());
    }

    /**
     * Gets the AKA table data
     * @return AKA table data in an {@link ArrayList}
     */
    public ArrayList<Aka> getAkas(){
        return akas;
    }

    /**
     * Gets the Character table data
     * @return Character table data in an {@link ArrayList}
     */
    public ArrayList<Character> getCharacters(){
        return characters;
    }

    /**
     * Gets the Crew table data
     * @return Crew table data in an {@link ArrayList}
     */
    public ArrayList<Crew> getCrews(){
        return crews;
    }

    /**
     * Gets the Episode table data
     * @return Episode table data in an {@link ArrayList}
     */
    public ArrayList<Episode> getEpisodes(){
        return episodes;
    }

    /**
     * Gets the KnownFor table data
     * @return KnownFor table data in an {@link ArrayList}
     */
    public ArrayList<KnownFor> getKnownFor(){
        return knownFor;
    }

    /**
     * Gets the Person table data
     * @return Person table data in an {@link ArrayList}
     */
    public ArrayList<Person> getPeople(){
        return people;
    }

    /**
     * Gets the Principle table data
     * @return Principle table data in an {@link ArrayList}
     */
    public ArrayList<Principal> getPrincipals(){
        return principals;
    }

    /**
     * Gets the Profession table data
     * @return Profession table data in an {@link ArrayList}
     */
    public ArrayList<Profession> getProfessions(){
        return professions;
    }

    /**
     * Gets the Rating table data
     * @return Rating table data in an {@link ArrayList}
     */
    public ArrayList<Rating> getRatings(){
        return ratings;
    }

    /**
     * Gets the Title table data
     * @return Title table data in an {@link ArrayList}
     */
    public ArrayList<Title> getTitles(){
        return titles;
    }

    /**
     * Gets the Genre table data
     * @return Genre table data in an {@link ArrayList}
     */
    public ArrayList<Genre> getGenres(){
        return genres;
    }

    /**
     * Gens the AKA table data from TitleAKAs
     * @param titleAKAs TitleAKA table input
     */
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

    /**
     * Gens the Character table data from TitlePrincipals
     * @param titlePrincipals TitlePrincipals table input
     */
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

    /**
     * Gens the Crew table data from TitleCrew
     * @param titleCrews TitleCrew table input
     */
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

    /**
     * Gens the Episode table data from TitleEpisode
     * @param titleEpisodes TitleEpisode table input
     */
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

    /**
     * Gens the KnownFor table data from NameBasic
     * @param nameBasics NameBasic table input
     */
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

    /**
     * Gens the Person table data from NameBasic
     * @param nameBasics NameBasic table input
     */
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

    /**
     * Gens the Principle table data from TitlePrincipal
     *
     * @param titlePrincipals TitlePrincipal table input
     */
    private void genPrincipals(ArrayList<TitlePrincipal> titlePrincipals){
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

    /**
     * Gens the Profession table data from NameBasic
     * @param nameBasics NameBasic table input
     */
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

    /**
     * Gens the Rating table data from TitleRating
     * @param titleRatings TitleRating input
     */
    private void genRatings(ArrayList<TitleRating> titleRatings){
        for(TitleRating titleRating : titleRatings){
            ratings.add(new Rating(
                    titleRating.getTconst(),
                    titleRating.getAverageRating(),
                    titleRating.getNumVotes()
            ));
        }
    }

    /**
     * Gens the Title table data from TitleBasic
     * @param titleBasics TitleBasic table input
     */
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

    /**
     * Gens the Genre table data from TitleBasic
     * @param titleBasics TitleBasic table input
     */
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
