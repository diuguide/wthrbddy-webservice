package com.home.weatherbuddy.repository;

import com.home.weatherbuddy.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
}
