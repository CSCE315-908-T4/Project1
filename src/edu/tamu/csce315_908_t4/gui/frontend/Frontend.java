package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Frontend extends Application{
    private IBackend backend;
    private ExecutorService executorService;
    private Stage primaryStage;
    private SelectWindow selectWindow;
    private SeparationWindow separationWindow;
    private RecommendationWindow recommendationWindow;


    public static void main(String[] args){
        Application.launch(args);
    }

    /**
     * Creates GUI
     */
    public Frontend(){
        executorService = Executors.newCachedThreadPool();
        backend = IBackend.getCurrent(executorService);

        primaryStage = null;
        selectWindow = new SelectWindow(this);
        separationWindow = new SeparationWindow(this, backend, executorService);
        recommendationWindow = new RecommendationWindow(this, backend, executorService);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        changeScene(selectWindow);
        primaryStage.show();
    }

    /**
     * switch to the scene we want
     *
     * @param window
     */
    private void changeScene(IWindow window){
        primaryStage.setScene(window.getScene());
        primaryStage.setTitle(window.getTitle());
    }

    /**
     * Return to previous/Start window
     * @param actionEvent
     */
    public void backAction(ActionEvent actionEvent){
        changeScene(selectWindow);
    }

    /**
     * go to SeparationWindow
     * @param actionEvent
     */
    public void separationAction(ActionEvent actionEvent){
        changeScene(separationWindow);
    }

    /**
     * go to Recommendation Window
     * @param actionEvent
     */
    public void recommendationAction(ActionEvent actionEvent){
        changeScene(recommendationWindow);
    }
}
