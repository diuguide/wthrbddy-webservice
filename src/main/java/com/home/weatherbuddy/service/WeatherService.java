package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.WeatherData;
import com.home.weatherbuddy.repository.WeatherDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {
    WeatherDataRepository weatherDataRepository;

    @Autowired
    public WeatherService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }
    public String returnWeatherString(Double temp, Double humidity, int station_id) {
        WeatherData weatherData = new WeatherData(temp, humidity, station_id);
        weatherData.setTimestamp(LocalDateTime.now());
        weatherDataRepository.save(weatherData);
        return "Temperature: " + weatherData.getTemp() + " Humidity: " + weatherData.getHumidity() + " Station ID: " + weatherData.getStationId();
    }
    public WeatherData getCurrentWeather(int stationId) {
        Pageable pageable = PageRequest.of(0, 1); // Fetch only the first result
        List<WeatherData> weatherDataList = weatherDataRepository.findLatestWeatherDataByStationId(stationId, pageable);
        return weatherDataList.isEmpty() ? null : weatherDataList.get(0);
    }

}
