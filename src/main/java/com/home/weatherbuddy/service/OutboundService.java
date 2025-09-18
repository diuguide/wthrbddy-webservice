package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OutboundService {

    private final Logger logger = LoggerFactory.getLogger(OutboundService.class);
    private final MemoryService memoryService = MemoryService.getInstance();

    public StationInstance getStationInstanceById(int stationId) {
        logger.info("Fetching station data for ID: " + stationId);
        String key = "station_" + stationId;
        StationInstance stationInstance;
        stationInstance = memoryService.read(key);
        logger.info("retreived instance in outbound service: " + stationInstance.toString());
        return memoryService.read(key); // Returns the StationInstance or null if not found
    }

}
