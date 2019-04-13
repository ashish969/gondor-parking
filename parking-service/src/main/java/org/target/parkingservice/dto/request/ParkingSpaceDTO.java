package org.target.parkingservice.dto.request;

import org.target.parkingservice.dto.parkingstructure.Floor;

import java.math.BigInteger;
import java.util.Objects;

public class ParkingSpaceDTO {

    private String parkingNumber;
    private Integer parkingLevel;
    private Integer floorNumber;

    public ParkingSpaceDTO() {
    }

    public ParkingSpaceDTO(String parkingNumber, Integer parkingLevel, Integer floorNumber) {
        this.parkingNumber = parkingNumber;
        this.parkingLevel = parkingLevel;
        this.floorNumber = floorNumber;
    }

    public String getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(String parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public Integer getParkingLevel() {
        return parkingLevel;
    }

    public void setParkingLevel(Integer parkingLevel) {
        this.parkingLevel = parkingLevel;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingSpaceDTO)) return false;
        ParkingSpaceDTO that = (ParkingSpaceDTO) o;
        return Objects.equals(getParkingNumber(), that.getParkingNumber()) &&
                Objects.equals(getParkingLevel(), that.getParkingLevel()) &&
                Objects.equals(getFloorNumber(), that.getFloorNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingNumber(), getParkingLevel(), getFloorNumber());
    }

    @Override
    public String toString() {
        return "ParkingSpaceDTO{" +
                "parkingNumber='" + parkingNumber + '\'' +
                ", parkingLevel=" + parkingLevel +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
