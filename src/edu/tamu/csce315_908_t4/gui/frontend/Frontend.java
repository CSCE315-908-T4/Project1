package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class Frontend extends Application{
    private IBackend backend;

    private Stage primaryStage;
    private SelectWindow selectWindow;
    private SeparationWindow separationWindow;
    private RecommendationWindow recommendationWindow;


    public static void main(String[] args){
        Application.launch(args);
    }

    public Frontend(){
        backend = IBackend.getCurrent();

        primaryStage = null;
        selectWindow = new SelectWindow(this);
        separationWindow = new SeparationWindow(this, backend);
        recommendationWindow = new RecommendationWindow(this, backend);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        changeScene(selectWindow);
        primaryStage.show();
    }

    private void changeScene(IWindow window){
        primaryStage.setScene(window.getScene());
        primaryStage.setTitle(window.getTitle());
    }

    public void backAction(ActionEvent actionEvent){
        changeScene(selectWindow);
    }

    public void separationAction(ActionEvent actionEvent){
        changeScene(separationWindow);
    }

    public void recommendationAction(ActionEvent actionEvent){
        changeScene(recommendationWindow);
    }
}
