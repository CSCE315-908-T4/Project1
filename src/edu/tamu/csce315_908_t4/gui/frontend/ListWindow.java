package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ListWindow extends Application implements IWindow {
    private Scene scene;
    private GridPane root;

    public ListWindow() {
    }

    public ListWindow(Frontend frontend, IBackend backend) {

        Button backButton = new Button("Back");
        backButton.setOnAction(frontend::backAction);


        root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        // add stuff
        root.add(backButton, 0, 10, 1, 1);

        scene = new Scene(root, 800, 600); // change size?
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public String getTitle() {
        return "Shortest List";
    }

    // testing only
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene());
        primaryStage.setTitle(getTitle());
        primaryStage.show();
    }
}
