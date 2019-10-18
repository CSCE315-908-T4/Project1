package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.BackendError;
import edu.tamu.csce315_908_t4.gui.backend.BackendErrorData;
import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import edu.tamu.csce315_908_t4.gui.backend.Nconst;
import edu.tamu.csce315_908_t4.gui.backend.arguments.RecommendationArg;
import edu.tamu.csce315_908_t4.gui.backend.progress.RecommendationProgress;
import edu.tamu.csce315_908_t4.gui.backend.result.RecommendationResult;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class RecommendationWindow implements IWindow {
    public GridPane page;
    boolean isSearching;
    private Frontend frontend;
    private IBackend backend;
    private Scene scene;
    private ExecutorService executorService;
    private TextField name;
    private TextField year;
    private TextField genre;
    private TextArea printResults;

    /**
     * @param frontend
     * @param backend
     * @param executorService
     */
    public RecommendationWindow(Frontend frontend, IBackend backend, ExecutorService executorService) {

        isSearching = false;
        this.frontend = frontend;
        this.backend = backend;
        this.executorService = executorService;
        //initializing all the elements in the page
        name = new TextField();
        year = new TextField();
        genre = new TextField();
        printResults = new TextArea();
        Label label1 = new Label("Actor");
        Label label2 = new Label("Genre");
        Label label3 = new Label("Year");
        Button back = new Button("Back");
        Button search = new Button("Generate Recommendations");
        back.setOnAction(frontend::backAction);
        search.setOnAction(this::searchAction);
        //setting up the page
        page = new GridPane();
        page.setAlignment(Pos.CENTER);
        page.addRow(0, label1, label2, label3);
        page.add(name, 0, 1, 2, 1);
        page.add(year, 1, 1, 2, 1);
        page.add(genre, 2, 1, 2, 1);
        page.add(printResults, 0, 5, 3, 8);
        page.add(back, 0, 15, 1, 1);
        page.add(search, 5, 1, 1, 1);
        page.setHgap(10);
        page.setVgap(10);
        scene = new Scene(page, 800, 600);
    }

    // testing
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * @param actionEvent
     */
    private void searchAction(javafx.event.ActionEvent actionEvent) {
        if(!isSearching) {
            isSearching = true;
            String actorText = name.getText();
            String genreText = genre.getText();
            int yearInt = Integer.parseInt(year.getText());

            executorService.submit(() -> {
                Future<BackendErrorData<ArrayList<Nconst>>> NconstFuture = backend.getNconst(actorText);
                try{
                    if(NconstFuture.get().isError()){
                        throw new RuntimeException(NconstFuture.get().error.exception);
                    }

                    Nconst initialNconst = NconstFuture.get().data.get(0);
                    RecommendationArg recommendationArg = new RecommendationArg(initialNconst,genreText, yearInt );
                    BackendError error = backend.getRecommendations(recommendationArg, this::progressCallback, this::resultCallback);
                    if(error.isError()){
                        throw new RuntimeException(error.exception);
                    }
                } catch(InterruptedException | ExecutionException e){
                    throw new RuntimeException(e);
                }
            });

        }
    }

    /**
     * function to check for errors in the recommendation result
     * @param error
     * @param result
     */
    private void resultCallback(BackendError error, RecommendationResult result) {
        if(error.isError()){
            throw new RuntimeException(error.exception);
        }
        printResults.setText(resultsToString(result));
        isSearching = false;
    }

    private void progressCallback(BackendError backendError, RecommendationProgress recommendationProgress) {
    }


    public Scene getScene() {
        return scene;
    }

    @Override
    public String getTitle() {
        return "FILMPEDIA Recommendations";
    }

    /**
     * using string builder to output the recommendation results
     *
     * @param results
     * @return
     */
    private String resultsToString(RecommendationResult results) {
        ArrayList<FinalResult> finalResults = new ArrayList<FinalResult>(results.subResults.size());
        for(RecommendationResult.SubResult subResult : results.subResults){
            finalResults.add(new FinalResult(backend.getPrimaryTitle(subResult.movieTconst),backend.getMovieYear(subResult.movieTconst), backend.getPrimaryName(subResult.actorNconst) ));
        }

        StringBuilder out = new StringBuilder();
        try{
            for(FinalResult finalResult : finalResults){
                out.append(finalResult.actorName.get().data)
                        .append(", ")
                        .append(finalResult.movieName.get().data)
                        .append(" (")
                        .append(finalResult.movieYear.get().data)
                        .append(")\n");
            }
            return out.toString();
        } catch(InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }

    // testing
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene());
        primaryStage.getTitle();
        primaryStage.show();
    }

    /**
     * class to sort the elements for the results output, and check for errors
     */
    private static class FinalResult{
        private final Future<BackendErrorData<String>> movieName;
        private final Future<BackendErrorData<Integer>> movieYear;
        private final Future<BackendErrorData<String>> actorName;

        private FinalResult(Future<BackendErrorData<String>> movieName, Future<BackendErrorData<Integer>> movieYear,
                            Future<BackendErrorData<String>> actorName){
            this.actorName = actorName;
            this.movieName = movieName;
            this.movieYear = movieYear;
        }
    }
}



