package com.home.weatherbuddy.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class StationInstance {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;
    private Double temp;
    private Double humidity;
    private int stationId;

    public StationInstance() {
    }

    public StationInstance(Double temp, Double humidity, int stationId) {
        this.temp = temp;
        this.humidity = humidity;
        this.stationId = stationId;
        this.timestamp = LocalDateTime.now();
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", stationId=" + stationId +
                "timestamp: " + timestamp +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
