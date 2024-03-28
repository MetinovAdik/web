package com.techdragons.web.aitwin.meer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // This method should contain the logic to check the database for multiple reports in the same area within a short time frame
        List<Earthquake> recentReports = earthquakeRepository.findRecentReports(earthquake.getLatitude(), earthquake.getLongitude());

        // Determine if an earthquake is happening based on the recent reports
        // This is a placeholder for your logic
        return recentReports.size() > 10;
    }
}
