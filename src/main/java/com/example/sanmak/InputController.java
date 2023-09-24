package com.example.sanmak;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InputController {

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

        Main main = new Main(name, radius, startTime, endTime, numLaps);

        Main.inputData();
    }
}
