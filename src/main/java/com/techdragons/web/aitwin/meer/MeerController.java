package com.techdragons.web.aitwin.meer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class MeerController {

    @Autowired
    private EarthquakeService earthquakeService;

    @PostMapping("/check")
    public boolean reportEarthquake(@RequestBody EarthquakeDTO earthquakeDTO) {
        return earthquakeService.processReport(earthquakeDTO);
    }
}