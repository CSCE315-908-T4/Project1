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

public class ChoiceWindow extends Application implements IWindow {
    private Scene scene;
    private GridPane root;

    public ChoiceWindow() {
    }

    public ChoiceWindow(Frontend frontend, IBackend backend) {
        Label label1 = new Label("Title of TV Series");
        TextField seriesTitle = new TextField();
        Button backButton = new Button("Back");
        backButton.setOnAction(frontend::backAction);
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String results = "TV Series: " + seriesTitle + "\n";
            //String addEpisode = "Season " + episode.seasonNumber + "Episode #" + episode.episodeNumber;
            //TextArea printResults = new TextArea(turnToString(results));
            //root.add(printResults, 0, 2, 3, 8);
        });

        root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        // add more stuff
        root.addRow(0, label1);
        root.addRow(1, seriesTitle);
        root.add(backButton, 0, 10, 1, 1);
        root.add(searchButton, 2, 10, 1, 1);
        scene = new Scene(root, 800, 600); // change size?
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public String getTitle() {
        return "Top 10 Rated Episodes in a TV Series";
    }

    // testing only
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene());
        primaryStage.setTitle(getTitle());
        primaryStage.show();
    }
}
