package edu.tamu.csce315_908_t4.gui.frontend;

package com.jenkov.javafx.layouts;



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
    @Override
    public void Start(Stage primaryStage)
    {
        primaryStage.setTitle("FILMPEDIA recommendations");

        TextField Name = new TextField();
        TextField Year = new TextField();
        TextField Genre = new TextField();
        Button Back = new Button("Back");
        Button Search = new Button("Generate Recommendations");
        GridPane page = new GridPane();
        TableView table = new TableView();
        TableColumn<String, Person> nameColumn = new TableColumn<>("Actor Name");
        TableColumn<String, Title> yearColumn = new TableColumn<>("Year");
        TableColumn<String, Title> titleColumn = new TableColumn<>("Title");
        table.getColumns().add(titleColumn);
        table.getColumns().add(yearColumn);
        table.getColumns().add(nameColumn);
        page.getChildren().add(Name, 0,0,2,1 );
        page.getChildren().add(Year, 1,0,2,1);
        page.getChildren().add(Genre, 2,0,2,1);
        page.getChildren().add(Back, 0, 9, 1, 1);
        page.getChildren().add(Search, 9,9,1,1);
        page.setHgap(10);
        page.setVgap(10);
        Scene scene = new Scene(page, 300,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        Search.setOnAction(event -> {
            VBox vbox = new VBox(Back, table);
            Scene scene1 = new Scene(vbox, 150, 200);
            primaryStage.setScene(scene1);
            primaryStage.show();


        })

    }




}
