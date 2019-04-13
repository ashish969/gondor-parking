package org.target.parkinglotservice.model.parkingstructure;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ParkingLevel {
    UP("UP"),
    DOWN("DOWN");
    @JsonProperty("level")
    String level;

    ParkingLevel(String level) {
        this.level = level;
    }
}

