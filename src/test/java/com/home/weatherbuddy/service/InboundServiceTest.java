package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InboundServiceTest {

    private InboundService inboundService;
    private MemoryService memoryService;

    // Use distinct station IDs per test to avoid cross-test interference
    private int stationIdCreate;
    private int stationIdUpdate;
    private int stationIdNulls;

    @BeforeEach
    void setUp() {
        memoryService = new MemoryService();
        inboundService = new InboundService(memoryService);
        stationIdCreate = 1001;
        stationIdUpdate = 1002;
        stationIdNulls = 1003;

        // Ensure clean state for the keys we use
        memoryService.delete(key(stationIdCreate));
        memoryService.delete(key(stationIdUpdate));
        memoryService.delete(key(stationIdNulls));
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test
        memoryService.delete(key(stationIdCreate));
        memoryService.delete(key(stationIdUpdate));
        memoryService.delete(key(stationIdNulls));
    }

    @Test
    void processInboundData_createsNewStation_whenNotExists() {
        String key = key(stationIdCreate);
        assertFalse(memoryService.exists(key));

        Double temp = 22.5;
        Double humidity = 55.0;

        String result = inboundService.processInboundData(temp, humidity, stationIdCreate);

        assertEquals("Temperature: " + temp +
                " Humidity: " + humidity +
                " Station ID: " + stationIdCreate,
                result);

        assertTrue(memoryService.exists(key));
        StationInstance stored = memoryService.read(key);
        assertNotNull(stored);
        assertEquals(temp, stored.getTemp());
        assertEquals(humidity, stored.getHumidity());
        assertEquals(stationIdCreate, stored.getStationId());
        assertNotNull(stored.getTimestamp());
    }

    @Test
    void processInboundData_updatesExistingStation_whenExists() {
        String key = key(stationIdUpdate);

        // Seed existing station
        StationInstance existing = new StationInstance(10.0, 20.0, stationIdUpdate);
        memoryService.create(key, existing);
        assertTrue(memoryService.exists(key));
        StationInstance before = memoryService.read(key);
        assertNotNull(before.getTimestamp());
        java.time.LocalDateTime initialTimestamp = before.getTimestamp();

        Double newTemp = 30.5;
        Double newHumidity = 70.0;

        String result = inboundService.processInboundData(newTemp, newHumidity, stationIdUpdate);

        assertEquals("Temperature: " + newTemp +
                " Humidity: " + newHumidity +
                " Station ID: " + stationIdUpdate,
                result);

        StationInstance after = memoryService.read(key);
        assertNotNull(after);
        assertEquals(newTemp, after.getTemp());
        assertEquals(newHumidity, after.getHumidity());
        assertEquals(stationIdUpdate, after.getStationId());
        // Timestamp should be refreshed in update path
        assertNotNull(after.getTimestamp());
        assertNotEquals(initialTimestamp, after.getTimestamp());
    }

    @Test
    void processInboundData_allowsNullValues() {
        String key = key(stationIdNulls);
        assertFalse(memoryService.exists(key));

        Double temp = null;
        Double humidity = 42.0;

        String result = inboundService.processInboundData(temp, humidity, stationIdNulls);

        assertEquals("Temperature: " + temp +
                " Humidity: " + humidity +
                " Station ID: " + stationIdNulls,
                result);

        StationInstance stored = memoryService.read(key);
        assertNotNull(stored);
        assertNull(stored.getTemp());
        assertEquals(humidity, stored.getHumidity());
        assertEquals(stationIdNulls, stored.getStationId());
    }

    private static String key(int stationId) {
        return "station_" + stationId;
    }
}
