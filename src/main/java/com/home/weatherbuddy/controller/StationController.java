package com.home.weatherbuddy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController {

    private final Logger logger = LoggerFactory.getLogger(StationController.class);

    @PostMapping(value = "/register")
    public String registerStation(@RequestBody String station_id) {
        logger.info("Station registered: " + station_id);
        return "Station registered: " + station_id;
    }
}