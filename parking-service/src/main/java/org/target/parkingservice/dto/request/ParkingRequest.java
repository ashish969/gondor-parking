package org.target.parkingservice.dto.request;

import org.target.parkingservice.model.object.Vehicle;

import java.math.BigInteger;
import java.util.Objects;

public class ParkingRequest {
    private BigInteger parkingLotId;
    private VehicleDTO vehicle;
    private String ssr;
    private Boolean isCarPooled;

    public ParkingRequest() {
    }

    public ParkingRequest(BigInteger parkingLotId, VehicleDTO vehicle, String ssr, Boolean isCarPooled) {
        this.parkingLotId = parkingLotId;
        this.vehicle = vehicle;
        this.ssr = ssr;
        this.isCarPooled = isCarPooled;
    }

    public BigInteger getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(BigInteger parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public String getSsr() {
        return ssr;
    }

    public void setSsr(String ssr) {
        this.ssr = ssr;
    }

    public Boolean getCarPooled() {
        return isCarPooled;
    }

    public void setCarPooled(Boolean carPooled) {
        isCarPooled = carPooled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingRequest)) return false;
        ParkingRequest that = (ParkingRequest) o;
        return Objects.equals(getParkingLotId(), that.getParkingLotId()) &&
                Objects.equals(getVehicle(), that.getVehicle()) &&
                getSsr() == that.getSsr() &&
                Objects.equals(isCarPooled, that.isCarPooled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingLotId(), getVehicle(), getSsr(), isCarPooled);
    }

    @Override
    public String toString() {
        return "ParkingRequest{" +
                "parkingLotId=" + parkingLotId +
                ", vehicle=" + vehicle +
                ", ssr=" + ssr +
                ", isCarPooled=" + isCarPooled +
                '}';
    }
}