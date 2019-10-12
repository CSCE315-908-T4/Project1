package edu.tamu.csce315_908_t4.gui.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SelectWindow implements IWindow{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;

    private Scene scene;

    public SelectWindow(Frontend frontend){
        Button separationButton = new Button("Degrees of Actor Separation");
        Button listButton = new Button("Shortest List");
        Button choiceButton = new Button("Team Choice");
        Button recommendationButton = new Button("Recommend a Movie");

        separationButton.setOnAction(frontend::separationAction);
        recommendationButton.setOnAction(frontend::recommendationAction);

        GridPane gridPane = new GridPane();
        gridPane.add(separationButton, 0, 0);
        gridPane.add(listButton, 0, 1);
        gridPane.add(choiceButton, 1, 0);
        gridPane.add(recommendationButton, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        scene = new Scene(gridPane, WIDTH, HEIGHT);
    }

    @Override
    public Scene getScene(){
        return scene;
    }

    @Override
    public String getTitle(){
        return "Select";
    }
}
