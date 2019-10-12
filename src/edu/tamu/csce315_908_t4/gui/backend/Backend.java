package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.gui.backend.result.CharacterResult;
import edu.tamu.csce315_908_t4.gui.backend.result.RecommendationResult;
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

            //Stores nconst's parent data in the form <child, <parentNconst, linkTconst>>
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
                ArrayList<Pair<String, String>> nconsts = getRelatedNconsts(currentNconst);
                for(Pair<String, String> nconst : nconsts){
                    if(nconst.getKey().equals(targetNconst)){
                        found = true;
                        break;
                    }
                    if(!nconstMap.containsKey(nconst.getKey())){
                        queue.add(new Pair<>(nconst.getKey(), depth + 1));
                        nconstMap.put(nconst.getKey(), new Link(currentNconst, nconst.getValue()));
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
            String prevName = getName(initialNconst);
            while(!currentNconst.equals(initialNconst)){
                Link link = nconstMap.get(currentNconst);
                String name = getName(currentNconst);
                out.add(0, new CharacterResult(
                        name,
                        prevName,
                        getTitle(link.linkTconst),
                        getMovieYear(link.linkTconst)
                ));
                currentNconst = link.parentNconst;
                prevName = name;
            }
            return out;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<RecommendationResult> getRecommendations(RecommendationArgs args)
    {
        try{
            String actorNconst = getNconst(findClosestName(args.actor).getKey());



        }
        catch(SQLException e){
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

    private ArrayList<Pair<String, String>> getRelatedNconsts(String nconst) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery(
                "SELECT DISTINCT \"Principal\".nconst AS nconst FROM (\n" +
                        "SELECT DISTINCT \"Principal\".tconst AS tconst FROM \"Principal\" WHERE \"Principal\".nconst = " + genSQL(nconst) + "\n" +
                        ") AS \"PrincipalSub\" INNER JOIN \"Principal\" ON \"PrincipalSub\".tconst = \"Principal\".tconst"
        );
        ArrayList<Pair<String, String>> out = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            out.add(new Pair<>(resultSet.getString("nconst"), resultSet.getString("tconst")));
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
