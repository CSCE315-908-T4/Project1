package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ListWindow extends Application implements IWindow {
    private Scene scene;
    private GridPane root;

    public ListWindow() {
    }

    public ListWindow(Frontend frontend, IBackend backend) {
        TextField year1 = new TextField();
        TextField year2 = new TextField();
        Label label1 = new Label();
        Label label2 = new Label();
        Button search = new Button("Search");
        Button backButton = new Button("Back");
        backButton.setOnAction(frontend::backAction);
        search.setOnAction(event -> {

        });

        root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        // add stuff
        root.addRow(0, label1, label2);
        root.add(year1, 0,1,2,2);
        root.add(year2, 1, 1,2,2);
        root.add(backButton, 0, 10, 1, 1);
        root.add(search, 4,2,1,1);
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
