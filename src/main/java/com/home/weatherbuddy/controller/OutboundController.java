package com.home.weatherbuddy.controller;

import com.home.weatherbuddy.model.StationInstance;
import com.home.weatherbuddy.service.OutboundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/station")
public class OutboundController {

    private final Logger logger = LoggerFactory.getLogger(OutboundController.class);
    private final OutboundService outboundService;

    @Autowired
    public OutboundController(OutboundService outboundService) {
        this.outboundService = outboundService;
    }

    @GetMapping(value = "/{id}")
    public String returnCurrentTime(@PathVariable("id") int station_id, Model model) {
        StationInstance currentWeather = outboundService.getStationInstanceById(station_id);
        logger.info("retreived data in outbound controller: " + currentWeather.toString());
        if (currentWeather != null && currentWeather.getTimestamp() != null) {
            logger.info("Returning data for station ID: " + station_id);
            model.addAttribute("currentWeather", currentWeather);
            model.addAttribute("formattedTimestamp", currentWeather.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return "weather";
    }
}