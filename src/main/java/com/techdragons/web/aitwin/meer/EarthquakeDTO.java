package com.techdragons.web.aitwin.meer;
import java.sql.Timestamp;
import java.util.Date;

// Lombok annotations to reduce boilerplate getter/setter code
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarthquakeDTO {

    private Long id;
    private String deviceId;
    private Timestamp timestamp;
    private Double longitude;
    private Double latitude;

    // Add any other fields that are relevant for the data transfer

    // Default constructor
    public EarthquakeDTO() {
    }

    // Constructor to convert Entity to DTO
    public EarthquakeDTO(Earthquake earthquake) {
        this.id = earthquake.getId();
        this.deviceId = earthquake.getDeviceId();
        this.timestamp = earthquake.getTimestamp();
        this.longitude = earthquake.getLongitude();
        this.latitude = earthquake.getLatitude();
    }

    // Method to convert DTO back to Entity
    public Earthquake toEntity() {
        Earthquake earthquake = new Earthquake();
        earthquake.setDeviceId(this.deviceId);
        earthquake.setTimestamp(this.timestamp);
        earthquake.setLongitude(this.longitude);
        earthquake.setLatitude(this.latitude);
        return earthquake;
    }
}
