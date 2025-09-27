package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.springframework.stereotype.Service;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Service
public class OutboundService {
    private final MemoryService memoryService;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Constructor DI of Spring-managed singleton is intentional and safe")
    public OutboundService(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    public StationInstance getStationInstanceById(int stationId) {
        String key = "station_" + stationId;
        return memoryService.read(key); // Returns the StationInstance or null if not found
    }

}
