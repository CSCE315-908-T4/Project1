package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import edu.tamu.csce315_908_t4.gui.backend.arguments.StringArg;
import edu.tamu.csce315_908_t4.gui.backend.result.CharacterResult;
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

public class SeparationWindow extends Application implements IWindow {
    private Scene scene;
    private GridPane root;

    public SeparationWindow() {
    }

    public SeparationWindow(Frontend frontend, IBackend backend) {
        Label label1 = new Label("Actor 1");
        Label label2 = new Label("Actor 2");
        Label label3 = new Label("Exclude");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        Button backButton = new Button("Back");
        backButton.setOnAction(frontend::backAction);
        Button searchButton = new Button("Search");
        //searchButton.setDefaultButton(true);
        searchButton.setOnAction(actionEvent -> {
            // do the search
            String actor1 = text1.getText();
            String actor2 = text2.getText();
            String exclude = text3.getText();
            IBackend.SeparationArgs separationArgs = new IBackend.SeparationArgs(actor1, actor2);
            separationArgs.addExcludedActor(new StringArg(exclude, StringArg.Type.LIKE));
            ArrayList<CharacterResult> results = backend.getSeparation(separationArgs);
            TextArea printResults = new TextArea(turnToString(results));
            root.add(printResults, 0, 2, 3, 8);
        });

        root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.addRow(0, label1, label2, label3);
        root.addRow(1, text1, text2, text3);
        root.add(backButton, 0, 10, 1, 1);
        root.add(searchButton, 2, 10, 1, 1);

        scene = new Scene(root, 800, 600);
    }

    // for testing only
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public String getTitle() {
        return "Degrees of Actor Separation";
    }

    public String turnToString(ArrayList<CharacterResult> results) {
        String actor1 = results.get(0).prevActorName;
        String actor2 = results.get(results.size()).actorName;
        String line = "";
        for (CharacterResult cr : results) {
            line = cr.prevActorName + ", " + cr.actorName + ", " + cr.movieName + " (" + cr.movieYear + ")\n";
        }
        line = line + "There are " + results.size() + " degrees of separation between " + actor1 + " and " + actor2 + ".\n";
        return line;
    }

    // testing only
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene());
        primaryStage.setTitle("Degrees of Actor Separation");
        primaryStage.show();
    }
}