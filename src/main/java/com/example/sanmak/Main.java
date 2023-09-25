package com.example.sanmak;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private String name;
    private double radius;
    private String start_time;
    private String end_time;
    private float laps;
    private float speed;
    private float runTimeInSecs;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalTime startTime = null;
    private LocalTime endTime = null;

    public Main(String name, double radius, String start_time, String end_time, float laps) {
        this.name = name;
        this.radius = radius;
        this.start_time = start_time;
        this.end_time = end_time;
        this.laps = laps;
    }

    public void inputData() {

        try {
            startTime = LocalTime.parse(start_time, timeFormatter);

            // You can now use the startTime as needed
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            endTime = LocalTime.parse(end_time, timeFormatter);

            // You can now use the startTime as needed
        } catch (Exception e) {
            e.printStackTrace();
        }

        Duration timeDifference = Duration.between(startTime, endTime);

        runTimeInSecs = timeDifference.toSeconds();

        float runTimeInHours = runTimeInSecs / (60*60);

        float trackLength = (float) ((float) 2 * Math.PI * radius * laps) / 1000;

        speed = trackLength / runTimeInHours;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanmac_interview",
                    "root", "RICHmond@?12");

            Statement statement = connection.createStatement();

            String sqlQuery = "INSERT INTO runners (name, speed, radius, start_time, end_time, duration, lap_count)" +
                    "VALUES ('" + name + "','" + speed + "','" + radius + "','" + startTime + "','" + endTime + "','"
                    + runTimeInSecs + "','"+ laps + "')";

            statement.execute(sqlQuery);

            System.out.println("Added Successfully!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private float calculateSpeed() {
        Duration timeDifference = Duration.between(startTime, endTime);

        runTimeInSecs = timeDifference.toSeconds();

        float runTimeInHours = runTimeInSecs / (60*60);

        float trackLength = (float) ((float) 2 * Math.PI * radius * laps) / 1000;

        return speed = trackLength / runTimeInHours;
    }

    public void updateData() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanmac_interview",
                    "root", "RICHmond@?12");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM runners");

            while (resultSet.next()) {
                radius = resultSet.getFloat("radius");
                startTime = LocalTime.parse(resultSet.getString("start_time"), timeFormatter);
                endTime = LocalTime.parse(resultSet.getString("end_time"), timeFormatter);
                laps = resultSet.getInt("lap_count");

                Duration timeDifference = Duration.between(startTime, endTime);

                float runTimeInSecs = timeDifference.toSeconds();

                float runTimeInHours = runTimeInSecs / (60 * 60);

                float trackLength = (float) ((float) 2 * Math.PI * radius * laps) / 1000;

                float speed = trackLength / runTimeInHours;

                String updateSQL = "UPDATE runners SET speed = ?, duration = ? WHERE name = ?";

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

                    preparedStatement.setFloat(1, speed);
                    preparedStatement.setFloat(2, runTimeInSecs);
                    preparedStatement.setString(3, resultSet.getString("name"));

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getStartTime() {
        return start_time;
    }

    public void setStartTime(String startTime) {
        this.start_time = startTime;
    }

    public String getEndTime() {
        return end_time;
    }

    public void setEndTime(String endTime) {
        this.end_time = endTime;
    }

    public float getNumLaps() {
        return laps;
    }

    public void setNumLaps(int numLaps) {
        this.laps = numLaps;
    }
}
