package edu.tamu.csce315_908_t4.gui.frontend;

package com.jenkov.javafx.layouts;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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

        page.getChildren().add(Name, 0,0,1,1 );
        page.getChildren().add(Year, 1,0,1,1);
        page.getChildren().add(Genre, 2,0,1,1);
        page.getChildren().add(Back, 0, 9, 1, 1);
        page.getChildren().add(Search, 9,9,1,1);
        page.setHgap(10);
        page.setVgap(10);
        Scene scene = new Scene(page, 480,720 );
        primaryStage.setScene(scene);
        primaryStage.show();


    }




}
