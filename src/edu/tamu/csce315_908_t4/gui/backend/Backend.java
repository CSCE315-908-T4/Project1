package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.database.Database;
import edu.tamu.csce315_908_t4.gui.backend.arguments.*;
import edu.tamu.csce315_908_t4.gui.backend.result.EpisodeResult;
import edu.tamu.csce315_908_t4.gui.backend.result.PersonResult;
import edu.tamu.csce315_908_t4.gui.backend.result.Result;
import edu.tamu.csce315_908_t4.gui.backend.result.TitleResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Backend{
    private Connection conn;
    private int offset;
    public static final int LIMIT = 10;

    public Backend(){
        this(Database.getConnection());
    }

    public Backend(Connection conn){
        this.conn = conn;
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

    public void setOffset(int offset){
        this.offset = offset;
    }

    private String genSQL(String table, String column, IntArg intArg){
        return "\"" + table + "\".\"" + column + "\" " + intArg.type.sql + " " + intArg.value;
    }

    private String genSQL(String table, String column, StringArg stringArg){
        String out = "\"" + table + "\".\"" + column + "\" ";
        if(stringArg.type == StringArg.Type.NOT){
            out += "NOT ";
        }
        out += "LIKE \'%" + stringArg.value.replace("\'", "\'\'") + "%\'";
        return out;
    }

    private String genSQL(ArrayList<String> argumentSQL){
        if(argumentSQL.size() == 0){
            return "";
        }
        StringBuilder out = new StringBuilder("WHERE ");
        for(int x = 0; x < argumentSQL.size(); x++){
            out.append(argumentSQL.get(x)).append(" ");
            if(x != argumentSQL.size() - 1){
                out.append(" AND ");
            }
        }
        return out.toString();
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
        if(arguments.getRuntime() != null){
            argumentSQL.add(genSQL("Title", "runtimeMinutes", arguments.getRuntime()));
        }
        if(arguments.getSeasonNumber() != null){
            argumentSQL.add(genSQL("Episode", "seasonnumber", arguments.getSeasonNumber()));
        }
        if(arguments.getEpisodeNumber() != null){
            argumentSQL.add(genSQL("Episode", "episodenumber", arguments.getEpisodeNumber()));
        }
        try{
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM \"Title\" " +
                    "INNER JOIN \"Rating\" on \"Title\".tconst = \"Rating\".tconst " +
                    "INNER JOIN \"Episode\" on \"Title\".tconst = \"Episode\".tconst " +
                    genSQL(argumentSQL) + " LIMIT " + LIMIT + " OFFSET " + offset);
            return new EpisodeResult(resultSet);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private PersonResult personSearch(PersonArguments arguments){
        ArrayList<String> argumentSQL = new ArrayList<>();
        if(arguments.getName() != null){
            argumentSQL.add(genSQL("Person", "primaryName", arguments.getName()));
        }
        if(arguments.getBirthYear() != null){
            argumentSQL.add(genSQL("Person", "birthYear", arguments.getBirthYear()));
        }
        if(arguments.getDeathYear() != null){
            argumentSQL.add(genSQL("Person", "deathYear", arguments.getDeathYear()));
        }
        if(arguments.getProfession() != null){
            argumentSQL.add(genSQL("Profession", "profession", arguments.getProfession()));
        }
        try{
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM \"Person\" " +
                    "INNER JOIN \"Profession\" on \"Person\".nconst = \"Profession\".nconst " +
                    genSQL(argumentSQL) + " LIMIT " + LIMIT + " OFFSET " + offset);
            return new PersonResult(resultSet);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private TitleResult titleSearch(TitleArguments arguments){
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
        if(arguments.getRuntime() != null){
            argumentSQL.add(genSQL("Title", "runtimeMinutes", arguments.getRuntime()));
        }
        try{
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM \"Title\" " +
                    "INNER JOIN \"Rating\" on \"Title\".tconst = \"Rating\".tconst " +
                    genSQL(argumentSQL) + " LIMIT " + LIMIT + " OFFSET " + offset);
            return new TitleResult(resultSet);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
