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
        // Определение временного интервала
        Timestamp fiveMinutesAgo = new Timestamp(System.currentTimeMillis() - 300000); // 300,000 milliseconds = 5 minutes

        // Определение географического "отступа"
        double errorMargin = 0.01; // примерный "отступ" в градусах
        double minLatitude = earthquake.getLatitude() - errorMargin;
        double maxLatitude = earthquake.getLatitude() + errorMargin;
        double minLongitude = earthquake.getLongitude() - errorMargin;
        double maxLongitude = earthquake.getLongitude() + errorMargin;

        // Изменённый вызов репозитория
        List<Earthquake> recentReports = earthquakeRepository.findRecentReports(minLatitude, maxLatitude, minLongitude, maxLongitude, fiveMinutesAgo);

        // Группировка отчётов по deviceId для учёта уникальности
        Map<String, List<Earthquake>> reportsByDevice = recentReports.stream()
                .collect(Collectors.groupingBy(Earthquake::getDeviceId));

        // Подсчёт количества уникальных устройств
        long uniqueDeviceCount = reportsByDevice.keySet().size();

        // Определение, происходит ли землетрясение, на основе количества уникальных отчётов
        return uniqueDeviceCount >= 2; // Примерный порог для демонстрации
    }
}
