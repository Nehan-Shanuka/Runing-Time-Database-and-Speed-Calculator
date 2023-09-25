package com.example.sanmak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RuntimeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RuntimeApplication.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setTitle("Welcome to Runtime Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
