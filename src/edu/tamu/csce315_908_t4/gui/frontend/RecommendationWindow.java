package edu.tamu.csce315_908_t4.gui.frontend;


import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import edu.tamu.csce315_908_t4.gui.backend.result.RecommendationResult;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class RecommendationWindow implements IWindow {

        boolean isSearching;
        private Frontend frontend;
        private IBackend backend;
        private Scene scene;
        public GridPane page;
        private ExecutorService executorService;
        private TextField name;
        private TextField year;
        private TextField genre;
        public RecommendationWindow(Frontend frontend, IBackend backend, ExecutorService executorService)
        {
            isSearching = true;
            this.frontend = frontend;
            this.backend = backend;
            this.executorService = executorService;
            name = new TextField();
            year = new TextField();
            genre = new TextField();
            Label label1 = new Label("Actor");
            Label label2 = new Label("Genre");
            Label label3 = new Label("Year");
            Button back = new Button("Back");
            Button search = new Button("Generate Recommendations");
            back.setOnAction(frontend::backAction);
            TableView table = new TableView();
            search.setOnAction(this::searchAction);
            page = new GridPane();
            TableColumn<String, TableElements> nameColumn = new TableColumn<>("Actor Name");
            TableColumn<Integer, TableElements> yearColumn = new TableColumn<>("Year");
            TableColumn<String, TableElements> titleColumn = new TableColumn<>("Title");
            table.getColumns().add(titleColumn);
            table.getColumns().add(yearColumn);
            table.getColumns().add(nameColumn);
            page.addRow(0, label1, label2, label3);
            page.add(name, 0,1,2,1 );
            page.add(year, 1,1,2,1);
            page.add(genre, 2,1,2,1);
            page.add(back, 0, 6, 1, 1);
            page.add(search, 6,6,1,1);
            page.setHgap(10);
            page.setVgap(10);
            scene = new Scene(page, 800,600);
        }


    private void searchAction(javafx.event.ActionEvent actionEvent) {
//                //set up arguments for search
//                String actor = name.getText();
//                Nconst actorNconst = new Nconst(actor);
//                String Genre = genre.getText();
//                int Year = Integer.parseInt(year.getText());
//                RecommendationArg recommendationArgs= new RecommendationArg(actorNconst, Genre, Year);
//                //do search
//                ArrayList<RecommendationResult> results = backend.getRecommendations(recommendationArgs);
//                //populate table;
//                int i;
//                for(i = 0; i<5; i++){
//                    table.getItems().add(new TableElements(results.get(i).movieTitle, results.get(i).year,results.get(i).actorName));
//                }
//                //show scene
//                VBox vbox = new VBox(table, back);
//                scene = new Scene(vbox, 300, 400);
//            });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public Scene getScene() {
        return scene;
    }
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene());
        primaryStage.setTitle("FILMPEDIA Recommendations");
        primaryStage.show();
    }

    @Override
    public String getTitle() {
        return "FILMPEDIA Recommendations";
    }
    class TableElements {

        private String Title = null;
        private int Year = 0;
        private String Name = null;

        public TableElements() {
            Title = null;
            Year = 0;
            Name = null;
        }

        public TableElements(String Title, int Year, String Name) {
            this.Title = Title;
            this.Year = Year;
            this.Name = Name;
        }

    }
}



