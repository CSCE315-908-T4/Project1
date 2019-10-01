package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import edu.tamu.csce315_908_t4.imdbParser.inputDataType.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * All data to be output in object form.
 */
@SuppressWarnings({"unchecked", "WeakerAccess"})
public class OutputDataBlock{
    private HashMap<EOutputTable, ArrayList<? extends IOutputTable>> outputData;

    /**
     * Creates an {@link OutputDataBlock} form an {@link InputDataBlock}
     *
     * @param inputDataBlock The input data used to create output data
     */
    public OutputDataBlock(InputDataBlock inputDataBlock){
        outputData = new HashMap<>(EOutputTable.values().length);
        outputData.put(EOutputTable.CHARACTER_TABLE, new ArrayList<Character>(inputDataBlock.getTitlePrincipals().size()));
        outputData.put(EOutputTable.CREW_TABLE, new ArrayList<Crew>(inputDataBlock.getTitleCrews().size()));
        outputData.put(EOutputTable.EPISODE_TABLE, new ArrayList<Episode>(inputDataBlock.getTitleEpisodes().size()));
        outputData.put(EOutputTable.KNOWN_FOR_TABLE, new ArrayList<KnownFor>(inputDataBlock.getNameBasics().size()));
        outputData.put(EOutputTable.PERSON_TABLE, new ArrayList<Person>(inputDataBlock.getNameBasics().size()));
        outputData.put(EOutputTable.PRINCIPAL_TABLE, new ArrayList<Principal>(inputDataBlock.getTitlePrincipals().size()));
        outputData.put(EOutputTable.PROFESSION_TABLE, new ArrayList<Profession>(inputDataBlock.getNameBasics().size()));
        outputData.put(EOutputTable.RATING_TABLE, new ArrayList<Rating>(inputDataBlock.getTitleRatings().size()));
        outputData.put(EOutputTable.TITLE_TABLE, new ArrayList<Title>(inputDataBlock.getTitleBasics().size()));
        outputData.put(EOutputTable.GENRE_TABLE, new ArrayList<Genre>(inputDataBlock.getTitleBasics().size()));

        System.out.println("Converting Ratings");
        genRatings(inputDataBlock.getTitleRatings());
        inputDataBlock.getTitleRatings().clear();
        System.out.println("Converting Crews");
        genCrews(inputDataBlock.getTitleCrews());
        inputDataBlock.getTitleCrews().clear();
        System.out.println("Converting Episodes");
        genEpisodes(inputDataBlock.getTitleEpisodes());
        inputDataBlock.getTitleEpisodes().clear();
        System.out.println("Converting NameBasic");
        genKnownFors(inputDataBlock.getNameBasics());
        genPeople(inputDataBlock.getNameBasics());
        genProfessions(inputDataBlock.getNameBasics());
        inputDataBlock.getNameBasics().clear();
        System.out.println("Converting TitleBasic");
        genTitles(inputDataBlock.getTitleBasics());
        genGenres(inputDataBlock.getTitleBasics());
        inputDataBlock.getTitleBasics().clear();
        System.out.println("Converting Principals");
        genCharacters(inputDataBlock.getTitlePrincipals());
        genPrincipals(inputDataBlock.getTitlePrincipals());
        inputDataBlock.getTitlePrincipals().clear();
    }

    public HashMap<EOutputTable, ArrayList<? extends IOutputTable>> getOutputData(){
        return outputData;
    }

    /**
     * Gens the Character table data from TitlePrincipals
     * @param titlePrincipals TitlePrincipals table input
     */
    private void genCharacters(ArrayList<TitlePrincipal> titlePrincipals){
        ArrayList<Character> characters = (ArrayList<Character>) outputData.get(EOutputTable.CHARACTER_TABLE);
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
        ArrayList<Crew> crews = (ArrayList<Crew>) outputData.get(EOutputTable.CREW_TABLE);
        for(TitleCrew titleCrew : titleCrews){
            String[] directors = titleCrew.getDirectors().split(",");
            String[] writers = titleCrew.getWriters().split(",");
            for(String director : directors){
                crews.add(new Crew(
                        titleCrew.getTconst(),
                        director,
                        "Director"
                ));
            }
            for(String writer : writers){
                crews.add(new Crew(
                        titleCrew.getTconst(),
                        writer,
                        "Writer"
                ));
            }
        }
    }

    /**
     * Gens the Episode table data from TitleEpisode
     * @param titleEpisodes TitleEpisode table input
     */
    private void genEpisodes(ArrayList<TitleEpisode> titleEpisodes){
        ArrayList<Episode> episodes = (ArrayList<Episode>) outputData.get(EOutputTable.EPISODE_TABLE);
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
        ArrayList<KnownFor> knownFor = (ArrayList<KnownFor>) outputData.get(EOutputTable.KNOWN_FOR_TABLE);
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
        ArrayList<Person> people = (ArrayList<Person>) outputData.get(EOutputTable.PERSON_TABLE);
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
        ArrayList<Principal> principals = (ArrayList<Principal>) outputData.get(EOutputTable.PRINCIPAL_TABLE);
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
        ArrayList<Profession> professions = (ArrayList<Profession>) outputData.get(EOutputTable.PROFESSION_TABLE);
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
        ArrayList<Rating> ratings = (ArrayList<Rating>) outputData.get(EOutputTable.RATING_TABLE);
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
        ArrayList<Title> titles = (ArrayList<Title>) outputData.get(EOutputTable.TITLE_TABLE);
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
        ArrayList<Genre> genres = (ArrayList<Genre>) outputData.get(EOutputTable.GENRE_TABLE);
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
