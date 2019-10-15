package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.gui.backend.arguments.RecommendationArg;
import edu.tamu.csce315_908_t4.gui.backend.arguments.SeparationArg;
import edu.tamu.csce315_908_t4.gui.backend.multi.Backend;
import edu.tamu.csce315_908_t4.gui.backend.progress.RecommendationProgress;
import edu.tamu.csce315_908_t4.gui.backend.progress.SeparationProgress;
import edu.tamu.csce315_908_t4.gui.backend.result.RecommendationResult;
import edu.tamu.csce315_908_t4.gui.backend.result.SeparationResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public interface IBackend{
    static IBackend getCurrent(ExecutorService executorService){
        return new Backend(executorService);
    }

    Future<BackendErrorData<ArrayList<Nconst>>> getNconst(String primaryName);

    Future<BackendErrorData<ArrayList<Nconst>>> getNconst(Collection<String> primaryNames);

    Future<BackendErrorData<String>> getPrimaryName(Nconst nconst);

    Future<BackendErrorData<ArrayList<String>>> getPrimaryName(Collection<Nconst> nconsts);

    Future<BackendErrorData<ArrayList<Tconst>>> getTconst(String primaryTitle);

    Future<BackendErrorData<ArrayList<Tconst>>> getTconst(Collection<String> primaryTitles);

    Future<BackendErrorData<String>> getPrimaryTitle(Tconst tconst);

    Future<BackendErrorData<ArrayList<String>>> getPrimaryTitle(Collection<Tconst> tconsts);

    Future<BackendErrorData<Integer>> getMovieYear(Tconst tconst);

    Future<BackendErrorData<ArrayList<Integer>>> getMovieYear(ArrayList<Tconst> tconsts);

    BackendError getSeparation(SeparationArg args, ICallback<SeparationProgress> progressICallback,
                               ICallback<SeparationResult> resultICallback);

    BackendError getRecommendations(RecommendationArg recommendationArg,
                                    ICallback<RecommendationProgress> progressICallback,
                                    ICallback<RecommendationResult> resultICallback);
}
