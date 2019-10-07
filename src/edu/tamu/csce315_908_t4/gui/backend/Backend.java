package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.database.Database;
import edu.tamu.csce315_908_t4.gui.backend.arguments.*;
import edu.tamu.csce315_908_t4.gui.backend.result.EpisodeResult;
import edu.tamu.csce315_908_t4.gui.backend.result.PersonResult;
import edu.tamu.csce315_908_t4.gui.backend.result.Result;
import edu.tamu.csce315_908_t4.gui.backend.result.TitleResult;

import java.sql.Connection;
import java.util.ArrayList;

public class Backend{
    private Connection conn;
    private SearchState searchState;
    private int offset;

    public Backend(SearchState searchState){
        this(Database.getConnection(), searchState);
    }

    public Backend(Connection conn, SearchState searchState){
        this.conn = conn;
        this.searchState = searchState;
    }

    public Result search(Arguments arguments){
        if(arguments.getClass() == EpisodeArguments.class){
            return episodeSearch((EpisodeArguments) arguments);
        } else if(arguments.getClass() == PersonArguments.class){
            return personSearch((PersonArguments) arguments);
        } else if(arguments.getClass() == TitleArguments.class){
            return titleSearch((TitleArguments) arguments);
        } else{
            throw new RuntimeException("Unknown class: " + arguments.getClass().getName());
        }
    }

    private String genSQL(String table, String column, IntArg intArg){
        return "\"" + table + "\".\"" + column + "\" " + intArg.type.sql + " " + intArg.value;
    }

    private String genSQL(String table, String column, StringArg stringArg){
        return "\"" + table + "\".\"" + column + "\" " + stringArg.type.sql + " \'" + stringArg.value.replace("\'", "\'\'") + "\'";
    }

    private EpisodeResult episodeSearch(EpisodeArguments arguments){
        ArrayList<String> argumentSQL = new ArrayList<>();
        if(arguments.getNumVotes() != null){
            argumentSQL.add(genSQL("Rating", "numVotes", arguments.getNumVotes()));
        }
        if(arguments.getTitle() != null){
            argumentSQL.add(genSQL("Title", "primaryTitle", arguments.getTitle()));
        }
        if(arguments.getType() != null){
            argumentSQL.add(genSQL("Title", "titleType", arguments.getType()));
        }
        if(arguments.getAdult() != null){
            argumentSQL.add("\"Title\".\"isAdult\" = " + arguments.getAdult().toString());
        }
        if(arguments.getStartYear() != null){
            argumentSQL.add(genSQL("Title", "startYear", arguments.getStartYear()));
        }
        if(arguments.getEndYear() != null){
            argumentSQL.add(genSQL("Title", "endYear", arguments.getEndYear()));
        }
        if(arguments.getEpisodeNumber() != null){
            String sql = "\"Episode\".\"episodenumber\" ";
            switch(arguments.getEpisodeNumber().type){
                case MAX:
                    sql += "<=";
                    break;
                case MIN:
                    sql += ">=";
                    break;
                case NOT:
                    sql += "!=";
                    break;
                case EQUALS:
                    sql += "=";
                    break;
            }
            sql += " " + arguments.getEpisodeNumber().value;
            argumentSQL.add(sql);
        }
        return null;
    }

    private PersonResult personSearch(PersonArguments arguments){
        return null;
    }

    private TitleResult titleSearch(TitleArguments arguments){
        return null;
    }

    public enum SearchState{
        PERSON,
        TITLE,
        EPISODE,
    }
}
