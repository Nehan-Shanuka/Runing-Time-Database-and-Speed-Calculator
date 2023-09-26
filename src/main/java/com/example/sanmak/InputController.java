package com.example.sanmak;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

        inputData(runner);
    }

    private Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanmac_interview",
                    "root", "RICHmond@?12");

            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void calculateSpeedAndDuration(Runner runner) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime start_time = null;
        LocalTime end_time = null;

        try {
            start_time = LocalTime.parse(runner.getStartTime(), timeFormatter);

            // You can now use the startTime as needed
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            end_time = LocalTime.parse(runner.getEndTime(), timeFormatter);

            // You can now use the startTime as needed
        } catch (Exception e) {
            e.printStackTrace();
        }

        Duration timeDifference = Duration.between(start_time, end_time);

        float duration = timeDifference.toSeconds();
        runner.setDuration(duration);

        float runTimeInHours = duration / (60*60);

        float trackLength = (float) ((float) 2 * Math.PI * runner.getRadius() * runner.getLap()) / 1000;

        float speed = trackLength / runTimeInHours;
        runner.setSpeed(speed);
    }

    protected void inputData(Runner runner) {

        calculateSpeedAndDuration(runner);

        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();

            String sqlQuery = "INSERT INTO runners (name, radius, start_time, end_time, lap_count)" +
                    "VALUES ('" + runner.getName() + "','" + runner.getRadius() + "','" + runner.getStartTime() +
                    "','" + runner.getEndTime() + "','"+ runner.getLap() + "')";

            statement.execute(sqlQuery);

            System.out.println("Added Successfully!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
