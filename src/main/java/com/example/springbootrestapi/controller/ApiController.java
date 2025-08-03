package com.example.springbootrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApiController {

    private String latestTemperature;
    private String latestHumidity;

    @GetMapping("/receive/{temp}:{humidity}")
    public String receiveReadings(@PathVariable String temp, @PathVariable String humidity) {
        this.latestTemperature = temp;
        this.latestHumidity = humidity;
        return "Received readings: Temperature = " + temp + ", Humidity = " + humidity;
    }

    @GetMapping
    public String displayReadings(Model model) {
        model.addAttribute("temperature", latestTemperature);
        model.addAttribute("humidity", latestHumidity);
        return "index";
    }
}