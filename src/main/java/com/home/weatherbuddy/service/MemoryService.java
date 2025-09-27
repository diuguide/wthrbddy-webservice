package com.home.weatherbuddy.service;

import java.util.Map;

import com.home.weatherbuddy.model.StationInstance;
import org.springframework.stereotype.Service;

@Service
public class MemoryService {
    private final Map<String, StationInstance> storage;

    public MemoryService() {
        // Concurrent storage for thread-safety in a web app environment
        this.storage = new java.util.concurrent.ConcurrentHashMap<>();
    }

    public void create(String key, StationInstance value) {
        storage.put(key, value);
    }

    public StationInstance read(String key) {
        return storage.get(key);
    }

    public void update(String key, StationInstance value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
        }
    }

    public void delete(String key) {
        storage.remove(key);
    }

    public boolean exists(String key) {
        return storage.containsKey(key);
    }

    public String printAllContents() {
        StringBuilder sb = new StringBuilder("Memory Contents:\n");
        storage.forEach((key, value) -> sb.append(key).append(" -> ").append(value.toString()).append("\n"));
        return sb.toString();
    }
}
