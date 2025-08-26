package com.home.weatherbuddy.model;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class StationOut {

    private int stationId;
    private String stationName;
    private String location;
    @Id
    private Long id;

}
