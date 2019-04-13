package org.target.parkingservice.model.object;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.target.parkingservice.model.parkingprocess.Parking;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column
    private String vehicleNumberPlate;
    @Column
    private String vehicleType;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehicle") @JsonBackReference
    private Parking parking;

    public Vehicle() {
    }

    public Vehicle(BigInteger id, String vehicleNumberPlate, String vehicleType, Parking parking) {
        this.id = id;
        this.vehicleNumberPlate = vehicleNumberPlate;
        this.vehicleType = vehicleType;
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getId(), vehicle.getId()) &&
                Objects.equals(getVehicleNumberPlate(), vehicle.getVehicleNumberPlate()) &&
                Objects.equals(getVehicleType(), vehicle.getVehicleType()) &&
                Objects.equals(getParking(), vehicle.getParking());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVehicleNumberPlate(), getVehicleType(), getParking());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleNumberPlate='" + vehicleNumberPlate + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", parking=" + parking +
                '}';
    }
}
