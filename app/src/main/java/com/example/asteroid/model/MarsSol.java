package com.example.asteroid.model;

public class MarsSol {
    private String solNumber;
    private double averageTemp;
    private double minTemp;
    private double maxTemp;
    private double averagePressure;
    private double minPressure;
    private double maxPressure;
    private String season;

    // Constructor actualizado
    public MarsSol(String solNumber, double averageTemp, double minTemp, double maxTemp,
                   double averagePressure, double minPressure, double maxPressure, String season) {
        this.solNumber = solNumber;
        this.averageTemp = averageTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.averagePressure = averagePressure;
        this.minPressure = minPressure;
        this.maxPressure = maxPressure;
        this.season = season;
    }

    // Getters necesarios...
    public String getSolNumber() { return solNumber; }
    public double getAverageTemp() { return averageTemp; }
    public double getMinTemp() { return minTemp; }
    public double getMaxTemp() { return maxTemp; }
    public double getAveragePressure() { return averagePressure; }
    public double getMinPressure() { return minPressure; }
    public double getMaxPressure() { return maxPressure; }
    public String getSeason() { return season; }
}
