package com.home.weatherbuddy.controller;

import com.google.appengine.repackaged.org.apache.commons.logging.impl.Log4JLogger;
import com.home.weatherbuddy.model.WeatherData;
import com.home.weatherbuddy.service.WeatherService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/inbound")
public class WeatherController {
    private final WeatherService weatherService;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(WeatherController.class);

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
        if (currentWeather != null && currentWeather.getTimestamp() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy hh:mm:ss a");
            String formattedTimestamp = currentWeather.getTimestamp().format(formatter);
            logger.info("hitting the weather data controller");
            model.addAttribute("formattedTimestamp", formattedTimestamp);
        }
        model.addAttribute("currentWeather", currentWeather);
        return "weather";
    }



}
