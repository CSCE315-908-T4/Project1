package edu.tamu.csce315_908_t4.gui.frontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Frontend extends Application{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    Stage primaryStage;
    Scene selectScene;
    GridPane gridPane;
    Button degreesButton;
    Button listButton;
    Button choiceButton;
    Button suggestionButton;

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Select");
        this.primaryStage = primaryStage;
        degreesButton = new Button("Degrees of Actors");
        listButton = new Button("Shortest List");
        choiceButton = new Button("Team Choice");
        suggestionButton = new Button("Suggest a Movie");

        gridPane = new GridPane();
        gridPane.add(degreesButton, 0, 0);
        gridPane.add(listButton, 0, 1);
        gridPane.add(choiceButton, 1, 0);
        gridPane.add(suggestionButton, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        selectScene = new Scene(gridPane, WIDTH, HEIGHT);
        primaryStage.setScene(selectScene);

        primaryStage.show();
    }

    private void backAction(ActionEvent actionEvent) {
        // closes the window
        //Scene scene = (Scene)actionEvent.getScene();

    }
}
