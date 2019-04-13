package org.target.parkingservice.dto.parkingstructure;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ParkingLevel {
    UP("UP"),
    DOWN("DOWN");
    String level;

    ParkingLevel(String level) {
        this.level = level;
    }
}

