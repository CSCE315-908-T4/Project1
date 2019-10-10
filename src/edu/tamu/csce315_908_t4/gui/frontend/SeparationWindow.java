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
        TextField actor1 = new TextField();
        TextField actor2 = new TextField();
        TextField exclude = new TextField();
        TextArea results = new TextArea();
        Button backButton = new Button("Back");
        Button searchButton = new Button("Search");

        GridPane root = new GridPane();
        scene = new Scene(root, 1000, 800);
        root.setHgap(10);
        root.setVgap(10);
        root.addRow(0, label1, label2, label3);
        root.addRow(1, actor1, actor2, exclude);
        root.add(results, 0, 2, 4, 8);
        root.add(backButton, 0, 10, 1, 1);
        root.add(searchButton, 3, 10, 1, 1);
        root.setPadding(new Insets(30, 10, 10, 30));

        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene(IBackend.getCurrent()));
        primaryStage.show();
    }
}