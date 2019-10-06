package edu.tamu.csce315_908_t4.imdbConverter;

import edu.tamu.csce315_908_t4.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static edu.tamu.csce315_908_t4.imdbConverter.Util.*;

@SuppressWarnings({"SqlWithoutWhere", "WeakerAccess"})
public class IMDBConverter{
    public static final int MAX_PER_QUERY = 10000;

    private static ExecutorService executorService;

    public static void main(String[] args){
        int threads = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(threads);
        System.out.println("Beginning with " + threads + " threads");
        System.out.println("Obtaining Connection");
        Connection conn = Database.getConnection();
        System.out.println("Connection Obtained");
        executorService.submit(() -> sqlTry(() -> convertNameBasics(conn)));
        executorService.submit(() -> sqlTry(() -> convertTitleBasics(conn)));
        executorService.submit(() -> sqlTry(() -> convertTitleCrew(conn)));
        executorService.submit(() -> sqlTry(() -> convertTitleEpisode(conn)));
        executorService.submit(() -> sqlTry(() -> convertTitlePrincipal(conn)));
        executorService.submit(() -> sqlTry(() -> convertTitleRatings(conn)));
    }

    @SuppressWarnings("SqlResolve")
    private static void convertType(Connection conn, EInputTable fromTable, EOutputTable[] toTables, IConvertFunction convertFunction) throws SQLException{
        System.out.println("Beginning conversion of " + fromTable.readableName);
        ResultSet numRowsQ = conn.createStatement().executeQuery("SELECT count(*) FROM \"" + fromTable.tableName + "\"");
        numRowsQ.next();
        int numRows = numRowsQ.getInt("count");
        Statement deleteOld = conn.createStatement();
        for(EOutputTable toTable : toTables){
            deleteOld.addBatch("DELETE FROM \"" + toTable.tableName + "\"");
        }
        deleteOld.executeBatch();

        for(int off = 0; off < numRows; off += MAX_PER_QUERY){
            int offset = off;
            executorService.submit(() -> {
                System.out.println(fromTable.readableName + ": " + offset + "/" + numRows);
                try{
                    ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM \"" + fromTable.tableName + "\" LIMIT " + MAX_PER_QUERY + " OFFSET " + offset);
                    HashMap<EOutputTable, Statement> outputStatements = new HashMap<>(toTables.length);
                    for(EOutputTable toTable : toTables){
                        outputStatements.put(toTable, conn.createStatement());
                    }
                    while(resultSet.next()){
                        convertFunction.convert(resultSet, outputStatements);
                    }
                    outputStatements.forEach((eOutputTable, statement) -> {
                        try{
                            statement.executeBatch();
                        } catch(SQLException e){
                            throw new RuntimeException(e);
                        }
                    });
                } catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void convertNameBasics(Connection conn) throws SQLException{
        convertType(
                conn,
                EInputTable.NAME_BASIC_TABLE,
                new EOutputTable[]{EOutputTable.PERSON_TABLE, EOutputTable.PROFESSION_TABLE, EOutputTable.KNOWN_FOR_TABLE},
                (resultSet, outputStatements) -> {
                    NameBasic nameBasic = new NameBasic(resultSet);
                    outputStatements.get(EOutputTable.PERSON_TABLE).addBatch("INSERT INTO \"Person\" (nconst, \"primaryName\", \"birthYear\", \"deathYear\") VALUES (" + toSQL(nameBasic.nconst) + ", " + toSQL(nameBasic.primaryName) + ", " + toSQL(nameBasic.birthYear) + ", " + toSQL(nameBasic.deathYear) + ")");
                    if(nameBasic.primaryProfession != null){
                        String[] professions = nameBasic.primaryProfession.split(",");
                        for(String profession : professions){
                            outputStatements.get(EOutputTable.PROFESSION_TABLE).addBatch("INSERT INTO \"Profession\" (nconst, profession) VALUES (" + toSQL(nameBasic.nconst) + ", " + toSQL(profession) + ")");
                        }
                    }
                    if(nameBasic.knownForTitles != null){
                        String[] knownFors = nameBasic.knownForTitles.split(",");
                        for(String knownFor : knownFors){
                            outputStatements.get(EOutputTable.KNOWN_FOR_TABLE).addBatch("INSERT INTO \"Known For\" (nconst, tconst) VALUES (" + toSQL(nameBasic.nconst) + ", " + toSQL(knownFor) + ")");
                        }
                    }
                }
        );
    }

    private static void convertTitleBasics(Connection conn) throws SQLException{
        convertType(
                conn,
                EInputTable.TITLE_BASIC_TABLE,
                new EOutputTable[]{EOutputTable.TITLE_TABLE, EOutputTable.GENRE_TABLE},
                (resultSet, outputStatements) -> {
                    TitleBasic titleBasic = new TitleBasic(resultSet);
                    outputStatements.get(EOutputTable.TITLE_TABLE).addBatch("INSERT INTO \"Title\" (tconst, \"titleType\", \"primaryTitle\", \"originalTitle\", \"isAdult\", \"startYear\", \"endYear\", \"runtimeMinutes\") VALUES (" + toSQL(titleBasic.tconst) + ", " + toSQL(titleBasic.titleType) + ", " + toSQL(titleBasic.primaryTitle) + ", " + toSQL(titleBasic.originalTitle) + ", " + toSQL(titleBasic.isAdult) + ", " + toSQL(titleBasic.startYear) + ", " + toSQL(titleBasic.endYear) + ", " + toSQL(titleBasic.runtimeMinutes) + ")");
                    if(titleBasic.genres != null){
                        String[] genres = titleBasic.genres.split(",");
                        for(String genre : genres){
                            outputStatements.get(EOutputTable.GENRE_TABLE).addBatch("INSERT INTO \"Genre\" (tconst, genre) VALUES (" + toSQL(titleBasic.tconst) + ", " + toSQL(genre) + ")");
                        }
                    }
                }
        );
    }

    private static void convertTitleCrew(Connection conn) throws SQLException{
        convertType(
                conn,
                EInputTable.TITLE_CREW_TABLE,
                new EOutputTable[]{EOutputTable.CREW_TABLE},
                (resultSet, outputStatements) -> {
                    TitleCrew titleCrew = new TitleCrew(resultSet);
                    if(titleCrew.directors != null){
                        String[] directors = titleCrew.directors.split(",");
                        for(String director : directors){
                            outputStatements.get(EOutputTable.CREW_TABLE).addBatch("INSERT INTO \"Crew\" (tconst, nconst, type) VALUES (" + toSQL(titleCrew.tconst) + ", " + toSQL(director) + ", 'director')");
                        }
                    }
                    if(titleCrew.writers != null){
                        String[] writers = titleCrew.writers.split(",");
                        for(String writer : writers){
                            outputStatements.get(EOutputTable.CREW_TABLE).addBatch("INSERT INTO \"Crew\" (tconst, nconst, type) VALUES (" + toSQL(titleCrew.tconst) + ", " + toSQL(writer) + ", 'writer')");
                        }
                    }
                }
        );
    }

    private static void convertTitleEpisode(Connection conn) throws SQLException{
        convertType(
                conn,
                EInputTable.TITLE_EPISODE_TABLE,
                new EOutputTable[]{EOutputTable.EPISODE_TABLE},
                (resultSet, outputStatements) -> {
                    TitleEpisode titleEpisode = new TitleEpisode(resultSet);
                    outputStatements.get(EOutputTable.EPISODE_TABLE).addBatch("INSERT INTO \"Episode\" (tconst, parenttconst, seasonnumber, episodenumber) VALUES (" + toSQL(titleEpisode.tconst) + ", " + toSQL(titleEpisode.parentTconst) + ", " + toSQL(titleEpisode.seasonNumber) + ", " + toSQL(titleEpisode.episodeNumber) + ")");
                }
        );
    }

    private static void convertTitlePrincipal(Connection conn) throws SQLException{
        convertType(
                conn,
                EInputTable.TITLE_PRINCIPAL_TABLE,
                new EOutputTable[]{EOutputTable.PRINCIPAL_TABLE, EOutputTable.CHARACTER_TABLE},
                (resultSet, outputStatements) -> {
                    TitlePrincipal titlePrincipal = new TitlePrincipal(resultSet);
                    outputStatements.get(EOutputTable.PRINCIPAL_TABLE).addBatch("INSERT INTO \"Principal\" (tconst, ordering, nconst, category, job) VALUES (" + toSQL(titlePrincipal.tconst) + ", " + toSQL(titlePrincipal.ordering) + ", " + toSQL(titlePrincipal.nconst) + ", " + toSQL(titlePrincipal.category) + ", " + toSQL(titlePrincipal.job) + ")");
                    if(titlePrincipal.characters != null){
                        ArrayList<String> characters = parseArray(titlePrincipal.characters);
                        for(String character : characters){
                            outputStatements.get(EOutputTable.CHARACTER_TABLE).addBatch("INSERT INTO \"Character\" (tconst, nconst, character) VALUES (" + toSQL(titlePrincipal.tconst) + ", " + toSQL(titlePrincipal.nconst) + ", " + toSQL(character) + ")");
                        }
                    }
                }
        );
    }

    private static void convertTitleRatings(Connection conn) throws SQLException{
        convertType(
                conn,
                EInputTable.TITLE_RATING_TABLE,
                new EOutputTable[]{EOutputTable.RATING_TABLE},
                (resultSet, outputStatements) -> {
                    TitleRating titleRating = new TitleRating(resultSet);
                    outputStatements.get(EOutputTable.RATING_TABLE).addBatch("INSERT INTO \"Rating\" (tconst, \"averageRating\", \"numVotes\") VALUES (" + toSQL(titleRating.tconst) + ", " + toSQL(titleRating.averageRating) + ", " + toSQL(titleRating.numVotes) + ")");
                }
        );
    }
}
