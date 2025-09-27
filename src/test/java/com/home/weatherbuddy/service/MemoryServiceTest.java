package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.StationInstance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryServiceTest {

    private final MemoryService memoryService = MemoryService.getInstance();

    @Test
    @DisplayName("getInstance returns singleton instance")
    void getInstance_returnsSameSingleton() {
        MemoryService a = MemoryService.getInstance();
        MemoryService b = MemoryService.getInstance();
        assertSame(a, b, "MemoryService should be a singleton");
    }

    @Test
    @DisplayName("create + read stores and returns value")
    void createAndRead_returnsStoredInstance() {
        String key = "memtest-create-read";
        try {
            StationInstance value = new StationInstance(10.0, 20.0, 1);
            memoryService.create(key, value);

            StationInstance fetched = memoryService.read(key);
            assertSame(value, fetched, "read should return the same stored instance");
            assertTrue(memoryService.exists(key));
        } finally {
            memoryService.delete(key);
        }
    }

    @Test
    @DisplayName("update on existing key replaces value")
    void updateExisting_replacesValue() {
        String key = "memtest-update-exists";
        try {
            StationInstance original = new StationInstance(1.0, 2.0, 111);
            memoryService.create(key, original);

            StationInstance updated = new StationInstance(3.0, 4.0, 222);
            memoryService.update(key, updated);

            StationInstance fetched = memoryService.read(key);
            assertSame(updated, fetched, "update should replace the value for existing key");
        } finally {
            memoryService.delete(key);
        }
    }

    @Test
    @DisplayName("update on missing key does nothing")
    void updateNonExisting_doesNothing() {
        String key = "memtest-update-missing";
        // ensure clean state
        memoryService.delete(key);

        StationInstance candidate = new StationInstance(7.0, 8.0, 333);
        memoryService.update(key, candidate);

        assertFalse(memoryService.exists(key), "update should not create new entries");
        assertNull(memoryService.read(key), "read should be null for non-existent key");
    }

    @Test
    @DisplayName("delete removes entry and exists returns false")
    void delete_removesEntry() {
        String key = "memtest-delete";
        StationInstance value = new StationInstance(9.0, 10.0, 444);
        memoryService.create(key, value);

        assertTrue(memoryService.exists(key));
        memoryService.delete(key);

        assertFalse(memoryService.exists(key));
        assertNull(memoryService.read(key));
    }

    @Test
    @DisplayName("exists reflects presence/absence of key")
    void exists_behavesCorrectly() {
        String key = "memtest-exists";
        memoryService.delete(key);
        assertFalse(memoryService.exists(key));

        StationInstance value = new StationInstance(11.0, 12.0, 555);
        memoryService.create(key, value);
        try {
            assertTrue(memoryService.exists(key));
        } finally {
            memoryService.delete(key);
        }
        assertFalse(memoryService.exists(key));
    }

    @Test
    @DisplayName("printAllContents includes header and entries")
    void printAllContents_includesHeaderAndEntries() {
        String key1 = "memtest-print-1";
        String key2 = "memtest-print-2";
        StationInstance v1 = new StationInstance(13.0, 14.0, 666);
        StationInstance v2 = new StationInstance(15.0, 16.0, 777);
        try {
            memoryService.create(key1, v1);
            memoryService.create(key2, v2);

            String printed = memoryService.printAllContents();
            assertNotNull(printed);
            assertTrue(printed.startsWith("Memory Contents:\n"), "Should start with header");
            assertTrue(printed.contains(key1 + " -> StationInstance{"), "Should contain first key line");
            assertTrue(printed.contains(key2 + " -> StationInstance{"), "Should contain second key line");
        } finally {
            memoryService.delete(key1);
            memoryService.delete(key2);
        }
    }
}
