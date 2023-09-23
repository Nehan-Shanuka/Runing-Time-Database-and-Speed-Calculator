package com.example.sanmak;

import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    private static String name;
    private static double radius;
    private static String start_time;
    private static String end_time;
    private static int laps;

    public Main(String name, double radius, String start_time, String end_time, int laps) {
        Main.name = name;
        Main.radius = radius;
        Main.start_time =start_time;
        Main.end_time = end_time;
        Main.laps = laps;
    }

    public static void inputData() {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime startTime = null;
        LocalTime endTime = null;

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

    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanmac_interview",
                "root", "RICHmond@?12");

        Statement statement = connection.createStatement();

        String sqlQuery = "INSERT INTO runners (name, radius, start_time, end_time, lap_count)" +
                "VALUES ('"+name+"', '"+radius+"','"+startTime+"','"+endTime+"','"+laps+"')";

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

    public int getNumLaps() {
        return laps;
    }

    public void setNumLaps(int numLaps) {
        this.laps = numLaps;
    }
}
