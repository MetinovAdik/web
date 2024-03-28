package com.techdragons.web.aitwin.meer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EarthquakeRepository  extends JpaRepository<Earthquake, Long> {
    @Query("SELECT e FROM Earthquake e WHERE e.latitude = :latitude AND e.longitude = :longitude AND e.timestamp > :fiveMinutesAgo")
    List<Earthquake> findRecentReports(Double latitude, Double longitude, Timestamp fiveMinutesAgo);
}
