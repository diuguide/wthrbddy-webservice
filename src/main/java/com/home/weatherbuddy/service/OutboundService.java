package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.springframework.stereotype.Service;

@Service
public class OutboundService {
    private final MemoryService memoryService = MemoryService.getInstance();

    public StationInstance getStationInstanceById(int stationId) {
        String key = "station_" + stationId;
        return memoryService.read(key); // Returns the StationInstance or null if not found
    }

}
