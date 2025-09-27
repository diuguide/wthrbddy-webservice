package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutboundServiceTest {

    private OutboundService outboundService;
    private MemoryService memoryService;

    private int stationIdExisting;
    private int stationIdMissing;

    @BeforeEach
    void setUp() {
        outboundService = new OutboundService();
        memoryService = MemoryService.getInstance();

        stationIdExisting = 2001;
        stationIdMissing = 2002;

        // Ensure clean slate
        memoryService.delete(key(stationIdExisting));
        memoryService.delete(key(stationIdMissing));

        // Seed existing one
        StationInstance seeded = new StationInstance(18.3, 60.0, stationIdExisting);
        memoryService.create(key(stationIdExisting), seeded);
    }

    @AfterEach
    void tearDown() {
        memoryService.delete(key(stationIdExisting));
        memoryService.delete(key(stationIdMissing));
    }

    @Test
    void getStationInstanceById_returnsInstance_whenExists() {
        StationInstance result = outboundService.getStationInstanceById(stationIdExisting);
        assertNotNull(result);
        assertEquals(18.3, result.getTemp());
        assertEquals(60.0, result.getHumidity());
        assertEquals(stationIdExisting, result.getStationId());
        assertNotNull(result.getTimestamp());
    }

    @Test
    void getStationInstanceById_returnsNull_whenNotExists() {
        StationInstance result = outboundService.getStationInstanceById(stationIdMissing);
        assertNull(result);
    }

    private static String key(int stationId) {
        return "station_" + stationId;
    }
}
