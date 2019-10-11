package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SeparationWindow extends Application implements IWindow {
    private Scene scene;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public Scene getScene(IBackend backend) {
        Label label1 = new Label("Actor 1");
        Label label2 = new Label("Actor 2");
        Label label3 = new Label("Exclude");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        TextArea results = new TextArea();
        Button backButton = new Button("Back");
        //backButton.setOnAction(this::backAction);
        Button searchButton = new Button("Search");
        //searchButton.setDefaultButton(true);
        searchButton.setOnAction(actionEvent -> {
            // do the search
            String actor1 = text1.getText();
            String actor2 = text2.getText();
            String exclude = text3.getText();

        });

        GridPane root = new GridPane();
        scene = new Scene(root, 1000, 800);
        root.setHgap(10);
        root.setVgap(10);
        root.addRow(0, label1, label2, label3);
        root.addRow(1, text1, text2, text3);
        root.add(results, 0, 2, 4, 8);
        root.add(backButton, 0, 10, 1, 1);
        root.add(searchButton, 3, 10, 1, 1);
        root.setPadding(new Insets(30, 10, 10, 30));

        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene(IBackend.getCurrent()));
        primaryStage.setTitle("Find Degrees of Separation Between Two Actors");
        primaryStage.show();
    }
}