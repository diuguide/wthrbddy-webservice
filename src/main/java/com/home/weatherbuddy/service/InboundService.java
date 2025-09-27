package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.springframework.stereotype.Service;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Service
public class InboundService {

    private final MemoryService memoryService;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Constructor DI of Spring-managed singleton is intentional and safe")
    public InboundService(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

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
