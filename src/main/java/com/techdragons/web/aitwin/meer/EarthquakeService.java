package com.techdragons.web.aitwin.meer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EarthquakeService {

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    public boolean processReport(EarthquakeDTO earthquakeDTO) {
        Earthquake earthquake = new Earthquake();
        earthquake.setDeviceId(earthquakeDTO.getDeviceId());
        earthquake.setTimestamp(earthquakeDTO.getTimestamp());
        earthquake.setLatitude(earthquakeDTO.getLatitude());
        earthquake.setLongitude(earthquakeDTO.getLongitude());
        earthquakeRepository.save(earthquake);

        return checkForEarthquake(earthquake);
    }

    private boolean checkForEarthquake(Earthquake earthquake) {
        Timestamp fiveMinutesAgo = new Timestamp(System.currentTimeMillis() - 300000); // 300,000 milliseconds = 5 minutes
        List<Earthquake> recentReports = earthquakeRepository.findRecentReports(earthquake.getLatitude(), earthquake.getLongitude(), fiveMinutesAgo);

        // Determine if an earthquake is happening based on the recent reports
        return recentReports.size() > 10; // This is just an example threshold
    }
}
