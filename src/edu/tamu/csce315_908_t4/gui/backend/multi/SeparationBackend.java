package edu.tamu.csce315_908_t4.gui.backend.multi;

import edu.tamu.csce315_908_t4.gui.backend.*;
import edu.tamu.csce315_908_t4.gui.backend.arguments.SeparationArg;
import edu.tamu.csce315_908_t4.gui.backend.progress.SeparationProgress;
import edu.tamu.csce315_908_t4.gui.backend.result.SeparationResult;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SeparationBackend{
    public static final int DEPTH_LIMIT = 4;

    private final Connection conn;
    private final ExecutorService executorService;
    private final Backend backend;

    public SeparationBackend(Connection conn, ExecutorService executorService, Backend backend){
        this.conn = conn;
        this.executorService = executorService;
        this.backend = backend;
    }

    public BackendError getSeparation(SeparationArg args, ICallback<SeparationProgress> progressICallback,
                                      ICallback<SeparationResult> resultICallback){
        if(args == null){
            return BackendError.NULL_ARGS_ERROR(SeparationArg.class);
        }
        executorService.submit(() -> {
            Queue<Job> jobQueue = new LinkedList<>();
            ConcurrentHashMap<Nconst, Link> nconstLinkHashMap = new ConcurrentHashMap<>();
            boolean found = false;
            boolean depthReached = false;

            jobQueue.add(new Job(args.initialNconst, 0));
            nconstLinkHashMap.put(args.initialNconst, new Link(null, null));

            while(!found){
                ArrayList<Job> jobs = new ArrayList<>(jobQueue.size());

                //Build jobs and remove from queue
                while(jobQueue.size() > 0){
                    Job job = jobQueue.remove();
                    if(job.depth > DEPTH_LIMIT){
                        depthReached = true;
                        break;
                    }
                    jobs.add(job);
                }
                if(depthReached){
                    break;
                }

                ArrayList<Future<BackendErrorData<Boolean>>> futures = new ArrayList<>(jobs.size());
                for(Job job : jobs){
                    progressICallback.callback(BackendError.NO_ERROR(), new SeparationProgress(job.depth, job.nconst, SeparationProgress.State.START));
                    futures.add(executorService.submit(() -> {
                        try{

                            ArrayList<Pair<Nconst, Tconst>> relations = getRelated(job.nconst, args.getExcludedActor());
                            for(Pair<Nconst, Tconst> related : relations){
                                if(related.getKey().equals(args.targetNconst)){
                                    return new BackendErrorData<>(BackendError.NO_ERROR(), true);
                                }
                                synchronized(this){
                                    if(!nconstLinkHashMap.containsKey(related.getKey())){
                                        jobQueue.add(new Job(related.getKey(), job.depth + 1));
                                        nconstLinkHashMap.put(related.getKey(), new Link(job.nconst, related.getValue()));
                                    }
                                }
                            }
                            progressICallback.callback(BackendError.NO_ERROR(), new SeparationProgress(job.depth, job.nconst, SeparationProgress.State.DONE));
                            return new BackendErrorData<>(BackendError.NO_ERROR(), false);
                        } catch(SQLException e){
                            return new BackendErrorData<>(BackendError.SQL_ERROR(e), null);
                        }
                    }));
                }

                for(Future<BackendErrorData<Boolean>> future : futures){
                    BackendErrorData<Boolean> result;

                    try{
                        result = future.get();
                    } catch(InterruptedException | ExecutionException e){
                        resultICallback.callback(BackendError.GENERAL_ERROR(e), null);
                        return;
                    }

                    if(result.error.isError()){
                        resultICallback.callback(result.error, null);
                        return;
                    }

                    if(result.data){
                        found = true;
                    }
                }
            }

            if(!found){
                if(depthReached){
                    resultICallback.callback(BackendError.LIMIT_ERROR("Depth", DEPTH_LIMIT), null);
                }
                resultICallback.callback(BackendError.NOT_FOUND_ERROR(), null);
            }

            SeparationResult out = new SeparationResult(args);
            Nconst currentNconst = args.targetNconst;
            while(!currentNconst.equals(args.initialNconst)){
                Link link = nconstLinkHashMap.get(currentNconst);
                out.subResults.addFirst(new SeparationResult.SubResult(
                        currentNconst,
                        link.parentNconst,
                        link.linkTconst
                ));
                currentNconst = link.parentNconst;
            }
            resultICallback.callback(BackendError.NO_ERROR(), out);
        });
        return BackendError.NO_ERROR();
    }

    private ArrayList<Pair<Nconst, Tconst>> getRelated(Nconst nconst, Nconst excluded) throws SQLException{
        ResultSet resultSet = conn.createStatement().executeQuery(
                "SELECT DISTINCT \"Principal\".nconst AS nconst FROM (\n" +
                        "SELECT DISTINCT \"Principal\".tconst AS tconst FROM \"Principal\" WHERE \"Principal\".nconst = " + Backend.genSQL(nconst.value) + (excluded == null ? "" : " AND NOT \"Principal\".nconst = " + Backend.genSQL(excluded.value)) + "\n" +
                        ") AS \"PrincipalSub\" INNER JOIN \"Principal\" ON \"PrincipalSub\".tconst = \"Principal\".tconst"
        );
        ArrayList<Pair<Nconst, Tconst>> out = new ArrayList<>(resultSet.getFetchSize());
        while(resultSet.next()){
            out.add(new Pair<>(new Nconst(resultSet), new Tconst(resultSet)));
        }
        return out;
    }

    private static class Link{
        private final Nconst parentNconst;
        private final Tconst linkTconst;

        private Link(Nconst parentNconst, Tconst linkTconst){
            this.parentNconst = parentNconst;
            this.linkTconst = linkTconst;
        }
    }

    private static class Job{
        private final Nconst nconst;
        private final int depth;

        public Job(Nconst nconst, int depth){
            this.nconst = nconst;
            this.depth = depth;
        }
    }
}
