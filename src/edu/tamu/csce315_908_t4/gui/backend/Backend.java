package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.gui.backend.result.CharacterResult;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Backend implements IBackend{
    public static final int DEPTH_LIMIT = 6;

    private Connection conn;

    public Backend(){
        conn = Database.getConnection();
    }

    private static String genSQL(String string){
        return "\'" + string.replace("\'", "\'\'") + "\'";
    }

    @Override
    public Pair<String, Integer> findClosestName(String in){
        try{
            if(in == null){
                throw new RuntimeException("Null in!");
            }
            ResultSet resultSetName = conn.createStatement().executeQuery("SELECT \"Person\".\"primaryName\" FROM \"Person\" WHERE \"primaryName\" LIKE " + genSQL("%" + in + "%") + " LIMIT 1");
            resultSetName.next();
            ResultSet resultSetCount = conn.createStatement().executeQuery("SELECT count(*) FROM \"Person\" WHERE \"primaryName\" LIKE " + genSQL("%" + in + "%"));
            resultSetCount.next();
            return new Pair<>(resultSetName.getString("primaryName"), resultSetCount.getInt("count"));
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<CharacterResult> getSeparation(SeparationArgs args){
        try{
            String initialNconst = getNconst(findClosestName(args.initialActor).getKey());
            String targetNconst = getNconst(findClosestName(args.targetActor).getKey());

            Queue<Pair<String, Integer>> queue = new LinkedList<>();

            //Stores nconst's parent data in the form <child, <parentNconst, linkTconst
            HashMap<String, Link> nconstMap = new HashMap<>();
            boolean found = false;
            boolean depthReached = false;
            queue.add(new Pair<>(initialNconst, 0));
            nconstMap.put(initialNconst, null);
            while(!found && queue.size() > 0){
                String currentNconst = queue.peek().getKey();
                Integer depth = queue.remove().getValue();
                if(depth > DEPTH_LIMIT){
                    depthReached = true;
                    break;
                }
                ArrayList<String> moviesTconsts = getAllTconsts(currentNconst);
                for(String tconst : moviesTconsts){
                    ArrayList<String> nconsts = getAllNconsts(tconst);
                    for(String nconst : nconsts){
                        nconstMap.put(nconst, new Link(currentNconst, tconst));
                        if(nconst.equals(targetNconst)){
                            found = true;
                            break;
                        }
                        queue.add(new Pair<>(nconst, depth + 1));
                    }
                }
            }

            if(!found){
                if(depthReached){
                    throw new RuntimeException("Maximum Depth Reached");
                }
                ArrayList<CharacterResult> out = new ArrayList<>();
                out.add(new CharacterResult(getName(initialNconst), "None", "None", 0));
                return out;
            }

            ArrayList<CharacterResult> out = new ArrayList<>();
            String currentNconst = targetNconst;
            while(currentNconst != initialNconst){
                Link link = nconstMap.get(currentNconst);
                out.add(new CharacterResult(
                        getName(currentNconst),
                        getCharacterName(currentNconst, link.linkTconst),
                        getTitle(link.linkTconst),
                        getMovieYear(link.linkTconst)
                ));
                currentNconst = link.parentNconst;
            }
            return out;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private String getNconst(String name) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Person\".nconst FROM \"Person\" WHERE \"primaryName\" = " + genSQL(name) + " LIMIT 1");
        if(resultSet.getFetchSize() <= 0){
            throw new RuntimeException("Cannot find name: " + name);
        }
        resultSet.next();
        return resultSet.getString("nconst");
    }

    private String getName(String nconst) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Person\".\"primaryName\" FROM \"Person\" WHERE nconst = " + genSQL(nconst) + " LIMIT 1");
        if(resultSet.getFetchSize() <= 0){
            throw new RuntimeException("Cannot find nconst: " + nconst);
        }
        resultSet.next();
        return resultSet.getString("primaryName");
    }

    private String getTitle(String tconst) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Title\".\"primaryTitle\" FROM \"Title\" WHERE tconst = " + genSQL(tconst) + " LIMIT 1");
        if(resultSet.getFetchSize() <= 0){
            throw new RuntimeException("Cannot find tconst: " + tconst);
        }
        resultSet.next();
        return resultSet.getString("primaryTitle");
    }

    private String getCharacterName(String nconst, String tconst) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Character\".character FROM \"Character\" WHERE tconst = " + genSQL(tconst) + " AND nconst = " + genSQL(nconst) + " LIMIT 1");
        if(resultSet.getFetchSize() <= 0){
            return null;
        }
        resultSet.next();
        return resultSet.getString("character");
    }

    private int getMovieYear(String tconst) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Title\".\"startYear\" FROM \"Title\" WHERE tconst = " + genSQL(tconst) + " LIMIT 1");
        if(resultSet.getFetchSize() <= 0){
            throw new RuntimeException("Cannot find tconst: " + tconst);
        }
        resultSet.next();
        return resultSet.getInt("startYear");
    }

    private ArrayList<String> getAllTconsts(String nconst) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Principal\".tconst FROM \"Principal\" WHERE nconst = " + genSQL(nconst) + " AND (category = 'actor' OR category = 'actress' OR category = 'self')");
        ArrayList<String> out = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            out.add(resultSet.getString("tconst"));
        }
        return out;
    }

    private ArrayList<String> getAllNconsts(String tconst) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Principal\".nconst FROM \"Principal\" WHERE tconst = " + genSQL(tconst) + " AND (category = 'actor' OR category = 'actress' OR category = 'self')");
        ArrayList<String> out = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            out.add(resultSet.getString("nconst"));
        }
        return out;
    }

    private static class Link{
        private String parentNconst;
        private String linkTconst;

        private Link(String parentNconst, String linkTconst){
            this.parentNconst = parentNconst;
            this.linkTconst = linkTconst;
        }
    }
}
