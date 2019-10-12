package edu.tamu.csce315_908_t4.gui.frontend;


import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import edu.tamu.csce315_908_t4.gui.backend.arguments.StringArg;
import edu.tamu.csce315_908_t4.imdbParser.outputDataType.Person;
import edu.tamu.csce315_908_t4.imdbParser.outputDataType.Title;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecommendationWindow implements IWindow {
        private Scene scene;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public Scene getScene(IBackend backend) {
        TextField name = new TextField();
        TextField year = new TextField();
        TextField genre = new TextField();
        Button back = new Button("Back");
        Button search = new Button("Generate Recommendations");

        TableView table = new TableView();
        search.setOnAction(event -> {
            VBox vbox = new VBox(back, table);
            scene = new Scene(vbox, 150, 200);
        });
        GridPane page = new GridPane();
        TableColumn<String, Person> nameColumn = new TableColumn<>("Actor Name");
        TableColumn<String, Title> yearColumn = new TableColumn<>("Year");
        TableColumn<String, Title> titleColumn = new TableColumn<>("Title");
        table.getColumns().add(titleColumn);
        table.getColumns().add(yearColumn);
        table.getColumns().add(nameColumn);
        page.add(name, 0,0,2,1 );
        page.add(year, 1,0,2,1);
        page.add(genre, 2,0,2,1);
        page.add(back, 0, 9, 1, 1);
        page.add(search, 9,9,1,1);
        page.setHgap(10);
        page.setVgap(10);
        scene = new Scene(page, 300,400);
        return scene;
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getScene(IBackend.getCurrent()));
        primaryStage.setTitle("FILMPEDIA Recommendations");
        primaryStage.show();
    }

    @Override
    public String getTitle() {
        return null;
    }
}
