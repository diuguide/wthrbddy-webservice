package com.home.weatherbuddy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "station")
public class Station {

    private int stationId;
    private String stationName;
    private String location;
    @Id
    private Long id;

    public Station() {
    }

    public Station(int stationId, String stationName, String location) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.location = location;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", location='" + location + '\'' +
                ", id=" + id +
                '}';
    }
}
