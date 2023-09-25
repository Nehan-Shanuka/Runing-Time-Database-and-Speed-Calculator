package com.example.sanmak;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Runner {

    private String name;
    private float speed;
    private double radius;
    private String startTime;
    private String endTime;
    private float duration;
    private int lap;

    public Runner(String name, float speed, double radius, String startTime,
                  String endTime, float duration, int lap) {
        this.name = name;
        this.speed = speed;
        this.radius = radius;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.lap = lap;
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

    protected void inputData() {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime start_time = null;
        LocalTime end_time = null;

        try {
            start_time = LocalTime.parse(startTime, timeFormatter);

            // You can now use the startTime as needed
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            end_time = LocalTime.parse(endTime, timeFormatter);

            // You can now use the startTime as needed
        } catch (Exception e) {
            e.printStackTrace();
        }

        Duration timeDifference = Duration.between(start_time, end_time);

        duration = timeDifference.toSeconds();

        float runTimeInHours = duration / (60*60);

        float trackLength = (float) ((float) 2 * Math.PI * radius * lap) / 1000;

        speed = trackLength / runTimeInHours;

        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();

            String sqlQuery = "INSERT INTO runners (name, speed, radius, start_time, end_time, duration, lap_count)" +
                    "VALUES ('" + name + "','" + speed + "','" + radius + "','" + startTime + "','" + endTime + "','"
                    + duration + "','"+ lap + "')";

            statement.execute(sqlQuery);

            System.out.println("Added Successfully!!!");

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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }
}
