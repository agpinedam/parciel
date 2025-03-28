package com.example.asteroid.model;

public class MarsSol {
    private String solNumber;
    private double averageTemp;
    private double minTemp;
    private double maxTemp;
    private double averagePressure;
    private String season;

    public MarsSol(String solNumber, double averageTemp, double minTemp, double maxTemp, double averagePressure, String season) {
        this.solNumber = solNumber;
        this.averageTemp = averageTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.averagePressure = averagePressure;
        this.season = season;
    }

    // getters
    public String getSolNumber() { return solNumber; }
    public double getAverageTemp() { return averageTemp; }
    public double getMinTemp() { return minTemp; }
    public double getMaxTemp() { return maxTemp; }
    public double getAveragePressure() { return averagePressure; }
    public String getSeason() { return season; }
}
