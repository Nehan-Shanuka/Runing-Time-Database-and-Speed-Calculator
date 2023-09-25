package com.example.sanmak;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InputController extends HomeController {

    @FXML
    private TextField nameText;
    @FXML
    private TextField radiusText;
    @FXML
    private TextField startTimeText;
    @FXML
    private TextField endTimeText;
    @FXML
    private TextField numLapText;

    @FXML
    protected void addTheRunner() {

        String name = nameText.getText();
        double radius = Double.parseDouble(radiusText.getText());
        String startTime = startTimeText.getText();
        String endTime = endTimeText.getText();
        int numLaps = Integer.parseInt(numLapText.getText());

        Runner runner = new Runner(name, 0, radius, startTime, endTime, 0, numLaps);

        runner.inputData();
    }
}
