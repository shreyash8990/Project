package com.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/com/library/Login.fxml");
        
        if (fxmlLocation == null) {
            System.out.println("Could not find Login.fxml. Check your resources folder!");
            System.exit(1);
        }

        Parent root = FXMLLoader.load(fxmlLocation);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

