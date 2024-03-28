package com.techdragons.web.aitwin.meer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        // Группируем по deviceId, чтобы учитывать только уникальные сигналы от каждого устройства
        Map<String, List<Earthquake>> reportsByDevice = recentReports.stream()
                .collect(Collectors.groupingBy(Earthquake::getDeviceId));

        // Считаем количество уникальных устройств, отправивших сигнал
        long uniqueDeviceCount = reportsByDevice.keySet().size();

        // Determine if an earthquake is happening based on the unique reports
        return uniqueDeviceCount > 10; // This is just an example threshold
    }
}
