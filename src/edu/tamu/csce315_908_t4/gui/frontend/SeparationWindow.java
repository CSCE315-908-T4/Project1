package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.BackendError;
import edu.tamu.csce315_908_t4.gui.backend.BackendErrorData;
import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import edu.tamu.csce315_908_t4.gui.backend.Nconst;
import edu.tamu.csce315_908_t4.gui.backend.arguments.SeparationArg;
import edu.tamu.csce315_908_t4.gui.backend.progress.SeparationProgress;
import edu.tamu.csce315_908_t4.gui.backend.result.SeparationResult;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SeparationWindow implements IWindow{
    private boolean isSearching;
    private TextArea printResults;
    private IBackend backend;
    private Scene scene;
    private ExecutorService executorService;
    private TextField initialActor;
    private TextField targetActor;
    private TextField excludedActor;

    /**
     * window for the degrees of separation problem
     *
     * @param frontend
     * @param backend
     * @param executorService
     */
    @SuppressWarnings("WeakerAccess")
    public SeparationWindow(Frontend frontend, IBackend backend, ExecutorService executorService){
        this.backend = backend;
        this.executorService = executorService;
        isSearching = false;

        Label initialActorLabel = new Label("Initial Actor");
        Label targetActorLabel = new Label("Target Actor");
        Label excludedActorLabel = new Label("Excluded Actor");

        initialActor = new TextField();
        targetActor = new TextField();
        excludedActor = new TextField();

        Button backButton = new Button("Back");
        backButton.setOnAction(frontend::backAction);
        Button searchButton = new Button("Search");
        //searchButton.setDefaultButton(true); // possible feature; the enter key starts search
        searchButton.setOnAction(this::searchAction);

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.addRow(0, initialActorLabel, targetActorLabel, excludedActorLabel);
        root.addRow(1, initialActor, targetActor, excludedActor);
        root.add(backButton, 0, 10, 1, 1);
        root.add(searchButton, 2, 10, 1, 1);

        printResults = new TextArea("");
        root.add(printResults, 0, 2, 3, 8);

        scene = new Scene(root, 800, 600); // change size?
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public String getTitle() {
        return "Degrees of Actor Separation";
    }

    /**
     * used for search button
     * @param actionEvent
     */
    private synchronized void searchAction(ActionEvent actionEvent){
        if(!isSearching){
            isSearching = true;
            // do the search
            String initialActorText = initialActor.getText();
            String targetActorText = targetActor.getText();
            String excludedActorText = excludedActor.getText();

            executorService.submit(() -> {
                Future<BackendErrorData<ArrayList<Nconst>>> initialNconstFuture = backend.getNconst(initialActorText);
                Future<BackendErrorData<ArrayList<Nconst>>> targetNconstFuture = backend.getNconst(targetActorText);
                Future<BackendErrorData<ArrayList<Nconst>>> excludedNconstFuture = null;
                if(!excludedActorText.equals("")){
                    excludedNconstFuture = backend.getNconst(excludedActorText);
                }
                try{
                    if(initialNconstFuture.get().isError()){
                        throw new RuntimeException(initialNconstFuture.get().error.exception);
                    }
                    if(targetNconstFuture.get().isError()){
                        throw new RuntimeException(targetNconstFuture.get().error.exception);
                    }
                    if(excludedNconstFuture != null && excludedNconstFuture.get().isError()){
                        throw new RuntimeException(excludedNconstFuture.get().error.exception);
                    }

                    Nconst initialNconst = initialNconstFuture.get().data.get(0);
                    Nconst targetNconst = targetNconstFuture.get().data.get(0);
                    Nconst excludedNconst = null;

                    if(excludedNconstFuture != null){
                        excludedNconst = excludedNconstFuture.get().data.get(0);
                    }

                    SeparationArg separationArg = new SeparationArg(initialNconst, targetNconst);
                    separationArg.setExcludedActor(excludedNconst);
                    BackendError error = backend.getSeparation(separationArg, this::progressCallback, this::resultCallback);
                    if(error.isError()){
                        throw new RuntimeException(error.exception);
                    }
                } catch(InterruptedException | ExecutionException e){
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void progressCallback(BackendError error, SeparationProgress data){

    }

    /**
     *
     * @param error
     * @param result
     */
    private void resultCallback(BackendError error, SeparationResult result){
        if(error.isError()){
            throw new RuntimeException(error.exception);
        }
        printResults.setText(resultsToString(result));
        isSearching = false;
    }

    /**
     *
     * @param results
     * @return string for output
     */
    private String resultsToString(SeparationResult results){
        ArrayList<FinalResult> finalResults = new ArrayList<>(results.subResults.size());
        for(SeparationResult.SubResult subResult : results.subResults){
            finalResults.add(new FinalResult(
                    backend.getPrimaryName(subResult.childNconst),
                    backend.getPrimaryName(subResult.parentNconst),
                    backend.getPrimaryTitle(subResult.movieTconst),
                    backend.getMovieYear(subResult.movieTconst)
            ));
        }
        Future<BackendErrorData<String>> initialNameFuture = backend.getPrimaryName(results.separationArg.initialNconst);
        Future<BackendErrorData<String>> targetNameFuture = backend.getPrimaryName(results.separationArg.targetNconst);

        StringBuilder out = new StringBuilder();
        try{
            for(FinalResult finalResult : finalResults){
                out.append(finalResult.parentName.get().data)
                        .append(", ")
                        .append(finalResult.childName.get().data)
                        .append(", ")
                        .append(finalResult.movieName.get().data)
                        .append(" (")
                        .append(finalResult.movieYear.get().data)
                        .append(")\n");
            }
            out.append("There are ")
                    .append(results.subResults.size())
                    .append(" degrees of separation between ")
                    .append(initialNameFuture.get().data)
                    .append(" and ")
                    .append(targetNameFuture.get().data)
                    .append(".\n");
            return out.toString();
        } catch(InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    private static class FinalResult{
        private final Future<BackendErrorData<String>> childName;
        private final Future<BackendErrorData<String>> parentName;
        private final Future<BackendErrorData<String>> movieName;
        private final Future<BackendErrorData<Integer>> movieYear;

        private FinalResult(Future<BackendErrorData<String>> childName, Future<BackendErrorData<String>> parentName,
                            Future<BackendErrorData<String>> movieName, Future<BackendErrorData<Integer>> movieYear){
            this.childName = childName;
            this.parentName = parentName;
            this.movieName = movieName;
            this.movieYear = movieYear;
        }
    }
}