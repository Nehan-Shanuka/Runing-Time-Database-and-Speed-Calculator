package com.example.sanmak;

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
