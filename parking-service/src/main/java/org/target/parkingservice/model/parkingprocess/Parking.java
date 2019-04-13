package org.target.parkingservice.model.parkingprocess;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.target.parkingservice.model.object.Vehicle;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column
    private BigInteger parkingLotId;
    @OneToOne(cascade = CascadeType.ALL) @JsonManagedReference
    private Vehicle vehicle;
    @Column
    private String parkingNumber;
    @Column
    private Integer parkingLevel;
    @Column
    private Integer floorNumber;
    @Column
    private Long startTime;
    @Column
    private Long endTime;
    @Column
    private String ssr;

    public Parking() {
    }

    public Parking(BigInteger id, BigInteger parkingLotId, Vehicle vehicle, String parkingNumber, Integer parkingLevel, Integer floorNumber, Long startTime, Long endTime, String ssr) {
        this.id = id;
        this.parkingLotId = parkingLotId;
        this.vehicle = vehicle;
        this.parkingNumber = parkingNumber;
        this.parkingLevel = parkingLevel;
        this.floorNumber = floorNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ssr = ssr;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(BigInteger parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getSsr() {
        return ssr;
    }

    public void setSsr(String ssr) {
        this.ssr = ssr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parking)) return false;
        Parking parking = (Parking) o;
        return Objects.equals(getId(), parking.getId()) &&
                Objects.equals(getParkingLotId(), parking.getParkingLotId()) &&
                Objects.equals(getVehicle(), parking.getVehicle()) &&
                Objects.equals(getParkingNumber(), parking.getParkingNumber()) &&
                Objects.equals(getParkingLevel(), parking.getParkingLevel()) &&
                Objects.equals(getFloorNumber(), parking.getFloorNumber()) &&
                Objects.equals(getStartTime(), parking.getStartTime()) &&
                Objects.equals(getEndTime(), parking.getEndTime()) &&
                Objects.equals(getSsr(), parking.getSsr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getParkingLotId(), getVehicle(), getParkingNumber(), getParkingLevel(), getFloorNumber(), getStartTime(), getEndTime(), getSsr());
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", parkingLotId=" + parkingLotId +
                ", vehicle=" + vehicle +
                ", parkingNumber='" + parkingNumber + '\'' +
                ", parkingLevel=" + parkingLevel +
                ", floorNumber=" + floorNumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ssr='" + ssr + '\'' +
                '}';
    }
}