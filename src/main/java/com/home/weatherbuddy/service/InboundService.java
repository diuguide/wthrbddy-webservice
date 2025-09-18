package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class InboundService {

    private final MemoryService memoryService = MemoryService.getInstance();

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(InboundService.class);

    public String processInboundData(Double temp, Double humidity, int station_id) {
        String key = "station_" + station_id;
        StationInstance stationInstance;

        if (memoryService.exists(key)) {
            // Update existing station
            logger.info("Updating existing station: " + station_id);
            stationInstance = memoryService.read(key);
            logger.info("retreived instance: " + stationInstance.toString());
            stationInstance.setTemp(temp);
            stationInstance.setHumidity(humidity);
            stationInstance.setTimestamp(java.time.LocalDateTime.now());
            memoryService.update(key, stationInstance);
        } else {
            // Create new station
            logger.info("Creating new station: " + station_id);
            stationInstance = new StationInstance(temp, humidity, station_id);
            memoryService.create(key, stationInstance);
            logger.info("all data: " + memoryService.printAllContents());
        }

        return "Temperature: " + stationInstance.getTemp() +
                " Humidity: " + stationInstance.getHumidity() +
                " Station ID: " + stationInstance.getStationId();
    }



}
