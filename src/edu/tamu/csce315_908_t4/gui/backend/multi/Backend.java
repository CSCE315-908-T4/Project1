package edu.tamu.csce315_908_t4.gui.backend.multi;

import edu.tamu.csce315_908_t4.gui.backend.*;
import edu.tamu.csce315_908_t4.gui.backend.arguments.RecommendationArg;
import edu.tamu.csce315_908_t4.gui.backend.arguments.SeparationArg;
import edu.tamu.csce315_908_t4.gui.backend.progress.RecommendationProgress;
import edu.tamu.csce315_908_t4.gui.backend.progress.SeparationProgress;
import edu.tamu.csce315_908_t4.gui.backend.result.RecommendationResult;
import edu.tamu.csce315_908_t4.gui.backend.result.SeparationResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Backend implements IBackend{
    public static final int DEPTH_LIMIT = 6;

    private final Connection conn;
    private final ExecutorService executorService;

    SeparationBackend separationBackend;

    public Backend(ExecutorService executorService){
        conn = DatabaseData.getConnection();
        this.executorService = executorService;

        separationBackend = new SeparationBackend(conn, executorService, this);
    }

    public static String genSQL(String string){
        if(string == null){
            return "NULL";
        }
        return "\'" + string.replace("\'", "\'\'") + "\'";
    }

    @Override
    public Future<BackendErrorData<ArrayList<Nconst>>> getNconst(String primaryName){
        return executorService.submit(() -> {
            try{
                ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Person\".nconst FROM \"Person\" WHERE \"primaryName\" LIKE " + genSQL("%" + primaryName + "%"));
                return new BackendErrorData<>(BackendError.NO_ERROR(), Nconst.getNconsts(resultSet));
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<ArrayList<Nconst>>> getNconst(Collection<String> primaryNames){
        return executorService.submit(() -> {
            if(primaryNames.size() <= 0){
                return new BackendErrorData<>(BackendError.NO_ERROR(), new ArrayList<>());
            }
            try{
                StringBuilder sql = new StringBuilder("SELECT \"Person\".nconst FROM \"Person\" WHERE ");
                boolean first = true;
                for(String primaryName : primaryNames){
                    if(!first){
                        sql.append("OR ");  //Only if not first element add OR
                    }
                    sql.append("\"primaryName\" LIKE ").append(genSQL("%" + primaryName + "%")).append(" ");
                    first = false;
                }
                ResultSet resultSet = conn.createStatement().executeQuery(sql.toString());
                return new BackendErrorData<>(BackendError.NO_ERROR(), Nconst.getNconsts(resultSet));
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<String>> getPrimaryName(Nconst nconst){
        return executorService.submit(() -> {
            try{
                ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Person\".\"primaryName\" FROM \"Person\" WHERE nconst = " + genSQL(nconst.value));
                resultSet.next();
                return new BackendErrorData<>(BackendError.NO_ERROR(), resultSet.getString("primaryName"));
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<ArrayList<String>>> getPrimaryName(Collection<Nconst> nconsts){
        return executorService.submit(() -> {
            if(nconsts.size() <= 0){
                return new BackendErrorData<>(BackendError.NO_ERROR(), new ArrayList<>());
            }
            try{
                StringBuilder sql = new StringBuilder("SELECT \"Person\".\"primaryName\" WHERE ");
                boolean first = true;
                for(Nconst nconst : nconsts){
                    if(!first){
                        sql.append("OR ");
                    }
                    sql.append("nconst = ").append(genSQL(nconst.value)).append(" ");
                    first = false;
                }
                ResultSet resultSet = conn.createStatement().executeQuery(sql.toString());
                ArrayList<String> out = new ArrayList<>(resultSet.getFetchSize());
                while(resultSet.next()){
                    out.add(resultSet.getString("primaryName"));
                }
                return new BackendErrorData<>(BackendError.NO_ERROR(), out);
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<ArrayList<Tconst>>> getTconst(String primaryTitle){
        return executorService.submit(() -> {
            try{
                ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"Title\".tconst FROM \"Title\" WHERE \"primaryTitle\" LIKE " + genSQL("%" + primaryTitle + "%"));
                return new BackendErrorData<>(BackendError.NO_ERROR(), Tconst.getTconsts(resultSet));
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<ArrayList<Tconst>>> getTconst(Collection<String> primaryTitles){
        return executorService.submit(() -> {
            if(primaryTitles.size() <= 0){
                return new BackendErrorData<>(BackendError.NO_ERROR(), new ArrayList<>());
            }
            try{
                StringBuilder sql = new StringBuilder("SELECT \"Title\".\"tconst\" WHERE ");
                boolean first = true;
                for(String primaryTitle : primaryTitles){
                    if(!first){
                        sql.append("OR ");
                    }
                    sql.append("\"primaryTitle\" LIKE ").append(genSQL("%" + primaryTitle + "%")).append(" ");
                    first = false;
                }
                ResultSet resultSet = conn.createStatement().executeQuery(sql.toString());
                return new BackendErrorData<>(BackendError.NO_ERROR(), Tconst.getTconsts(resultSet));
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<String>> getPrimaryTitle(Tconst tconst){
        return executorService.submit(() -> {
            try{
                ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"primaryTitle\" FROM \"Title\" WHERE tconst = " + genSQL(tconst.value));
                resultSet.next();
                return new BackendErrorData<>(BackendError.NO_ERROR(), resultSet.getString("primaryTitle"));
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<ArrayList<String>>> getPrimaryTitle(Collection<Tconst> tconsts){
        return executorService.submit(() -> {
            if(tconsts.size() <= 0){
                return new BackendErrorData<>(BackendError.NO_ERROR(), new ArrayList<>());
            }
            try{
                StringBuilder sql = new StringBuilder("SELECT \"Title\".\"primaryTitle\" WHERE ");
                boolean first = true;
                for(Tconst tconst : tconsts){
                    if(!first){
                        sql.append("OR ");
                    }
                    sql.append("\"tconst\" = ").append(genSQL(tconst.value)).append(" ");
                    first = false;
                }
                ResultSet resultSet = conn.createStatement().executeQuery(sql.toString());
                ArrayList<String> out = new ArrayList<>(resultSet.getFetchSize());
                while(resultSet.next()){
                    out.add(resultSet.getString("primaryTitle"));
                }
                return new BackendErrorData<>(BackendError.NO_ERROR(), out);
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<Integer>> getMovieYear(Tconst tconst){
        return executorService.submit(() -> {
            try{
                ResultSet resultSet = conn.createStatement().executeQuery("SELECT \"startYear\" FROM \"Title\" WHERE tconst = " + genSQL(tconst.value));
                resultSet.next();
                return new BackendErrorData<>(BackendError.NO_ERROR(), resultSet.getInt("startYear"));
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public Future<BackendErrorData<ArrayList<Integer>>> getMovieYear(ArrayList<Tconst> tconsts){
        return executorService.submit(() -> {
            if(tconsts.size() <= 0){
                return new BackendErrorData<>(BackendError.NO_ERROR(), new ArrayList<>());
            }
            try{
                StringBuilder sql = new StringBuilder("SELECT \"Title\".\"startYear\" WHERE ");
                boolean first = true;
                for(Tconst tconst : tconsts){
                    if(!first){
                        sql.append("OR ");
                    }
                    sql.append("\"tconst\" = ").append(genSQL(tconst.value)).append(" ");
                    first = false;
                }
                ResultSet resultSet = conn.createStatement().executeQuery(sql.toString());
                ArrayList<Integer> out = new ArrayList<>(resultSet.getFetchSize());
                while(resultSet.next()){
                    out.add(resultSet.getInt("startYear"));
                }
                return new BackendErrorData<>(BackendError.NO_ERROR(), out);
            } catch(SQLException e){
                return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
            }
        });
    }

    @Override
    public BackendError getSeparation(SeparationArg args, ICallback<SeparationProgress> progressICallback,
                                      ICallback<SeparationResult> resultICallback){
        return separationBackend.getSeparation(args, progressICallback, resultICallback);
    }

    @Override
    public BackendError getRecommendations(RecommendationArg recommendationArg, ICallback<RecommendationProgress> progressICallback, ICallback<RecommendationResult> resultICallback){
        return null;
    }
}
