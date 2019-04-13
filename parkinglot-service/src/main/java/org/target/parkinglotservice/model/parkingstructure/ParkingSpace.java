package org.target.parkinglotservice.model.parkingstructure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class ParkingSpace {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column
    private String parkingNumber;
    @Column
    private Integer parkingLevel;
    @Column
    private Integer easeOfParking;
    @ManyToOne @JsonBackReference
    private Floor floor;

    public ParkingSpace() {
    }

    public ParkingSpace(BigInteger id, String parkingNumber, Integer parkingLevel, Integer easeOfParking, Floor floor) {
        this.id = id;
        this.parkingNumber = parkingNumber;
        this.parkingLevel = parkingLevel;
        this.easeOfParking = easeOfParking;
        this.floor = floor;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public Integer getEaseOfParking() {
        return easeOfParking;
    }

    public void setEaseOfParking(Integer easeOfParking) {
        this.easeOfParking = easeOfParking;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingSpace)) return false;
        ParkingSpace that = (ParkingSpace) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getParkingNumber(), that.getParkingNumber()) &&
                Objects.equals(getParkingLevel(), that.getParkingLevel()) &&
                Objects.equals(getEaseOfParking(), that.getEaseOfParking()) &&
                Objects.equals(getFloor(), that.getFloor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getParkingNumber(), getParkingLevel(), getEaseOfParking(), getFloor());
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", parkingNumber='" + parkingNumber + '\'' +
                ", parkingLevel=" + parkingLevel +
                ", easeOfParking=" + easeOfParking +
                ", floor=" + floor +
                '}';
    }
}
