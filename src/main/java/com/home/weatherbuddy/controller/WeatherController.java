package com.home.weatherbuddy.controller;

import com.home.weatherbuddy.model.WeatherData;
import com.home.weatherbuddy.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inbound")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{temp}-{humidity}-{station_id}")
    @ResponseBody
    public String getTempAndHumidity(@PathVariable("temp") Double temp, @PathVariable("humidity") Double humidity , @PathVariable("station_id") int system_id) {
        return weatherService.returnWeatherString(temp, humidity, system_id);
    }

    @GetMapping(value = "/current/{id}")
    public String returnCurrentTime(@PathVariable("id") int station_id, Model model) {
        WeatherData currentWeather = weatherService.getCurrentWeather(station_id);
        model.addAttribute("currentWeather", currentWeather);
        return "weather";
    }



}
