package com.home.weatherbuddy.repository;

import com.home.weatherbuddy.model.WeatherData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Integer> {
        // Query to fetch the newest inserted row by stationId
        @Query("SELECT wd FROM WeatherData wd WHERE wd.stationId = :station_id ORDER BY wd.timestamp DESC")
        List<WeatherData> findLatestWeatherDataByStationId(@Param("station_id") int station_id, Pageable pageable);
}