package com.example.sanmak;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void inputStage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("input-page.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Input a Runner");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void databaseShow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("database-page.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("The Database");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void backButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("home-page.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome to Runner Application");
        stage.setScene(scene);
        stage.show();
    }
}
