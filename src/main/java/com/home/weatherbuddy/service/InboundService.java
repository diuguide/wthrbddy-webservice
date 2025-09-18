package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.springframework.stereotype.Service;

@Service
public class InboundService {

    private final MemoryService memoryService = MemoryService.getInstance();

    public String processInboundData(Double temp, Double humidity, int station_id) {
        String key = "station_" + station_id;
        StationInstance stationInstance;

        if (memoryService.exists(key)) {
            // Update existing station
            stationInstance = memoryService.read(key);
            stationInstance.setTemp(temp);
            stationInstance.setHumidity(humidity);
            stationInstance.setTimestamp(java.time.LocalDateTime.now());
            memoryService.update(key, stationInstance);
        } else {
            // Create new station
            stationInstance = new StationInstance(temp, humidity, station_id);
            memoryService.create(key, stationInstance);
        }

        return "Temperature: " + stationInstance.getTemp() +
                " Humidity: " + stationInstance.getHumidity() +
                " Station ID: " + stationInstance.getStationId();
    }



}
