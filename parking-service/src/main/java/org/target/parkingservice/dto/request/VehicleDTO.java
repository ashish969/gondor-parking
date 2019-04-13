package org.target.parkingservice.dto.request;


import org.target.parkingservice.model.parkingprocess.Parking;

import java.math.BigInteger;
import java.util.Objects;

public class VehicleDTO {

    private String vehicleNumberPlate;
    private String vehicleType;

    public VehicleDTO() {
    }

    public VehicleDTO(String vehicleNumberPlate, String vehicleType) {
        this.vehicleNumberPlate = vehicleNumberPlate;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumberPlate() {
        return vehicleNumberPlate;
    }

    public void setVehicleNumberPlate(String vehicleNumberPlate) {
        this.vehicleNumberPlate = vehicleNumberPlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleDTO)) return false;
        VehicleDTO vehicle = (VehicleDTO) o;
        return
                Objects.equals(getVehicleNumberPlate(), vehicle.getVehicleNumberPlate()) &&
                Objects.equals(getVehicleType(), vehicle.getVehicleType());
    }

    @Override
    public int hashCode() {
        return Objects.hash( getVehicleNumberPlate(), getVehicleType());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", vehicleNumberPlate='" + vehicleNumberPlate + '\'' +
                ", vehicleType='" + vehicleType +
                '}';
    }
}
