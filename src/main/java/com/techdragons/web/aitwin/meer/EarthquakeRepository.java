package com.techdragons.web.aitwin.meer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EarthquakeRepository  extends JpaRepository<Earthquake, Long> {
    @Query("SELECT e FROM Earthquake e WHERE e.latitude BETWEEN :minLatitude AND :maxLatitude AND e.longitude BETWEEN :minLongitude AND :maxLongitude AND e.timestamp > :fiveMinutesAgo")
    List<Earthquake> findRecentReports(Double minLatitude, Double maxLatitude, Double minLongitude, Double maxLongitude, Timestamp fiveMinutesAgo);
}
