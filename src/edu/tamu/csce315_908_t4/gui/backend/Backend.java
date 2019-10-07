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

    private EpisodeResult episodeSearch(EpisodeArguments arguments){
        ArrayList<String> argumentSQL = new ArrayList<>();
        if(arguments.getNumVotes() != null){
            IntArg numVotes = arguments.getNumVotes();
            argumentSQL.add("\"Rating\".\"numVotes\" " + numVotes.type.sql + " " + numVotes.value);
        }
        if(arguments.getTitle() != null){
            StringArg title = arguments.getTitle();
            argumentSQL.add("\"Title\".\"primaryTitle\" " + title.type.sql + " \'" + title.value.replace("\'", "\'\'") + "\'");
        }
        if(arguments.getAdult() != null){
            argumentSQL.add("\"Title\".\"isAdult\" = " + arguments.getAdult().toString());
        }
        if(arguments.getEndYear() != null){
            arguments
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
