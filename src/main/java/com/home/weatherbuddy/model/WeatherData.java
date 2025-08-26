package com.home.weatherbuddy.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "weatherData")
public class WeatherData {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;
    private Double temp;
    private Double humidity;
    private int stationId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public WeatherData() {
    }

    public WeatherData(Double temp, Double humidity, int stationId) {
        this.temp = temp;
        this.humidity = humidity;
        this.stationId = stationId;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", stationId=" + stationId +
                ", id=" + id +
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
