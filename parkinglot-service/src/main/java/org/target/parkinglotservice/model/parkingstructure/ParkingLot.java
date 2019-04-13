package org.target.parkinglotservice.model.parkingstructure;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkingLot") @JsonManagedReference
    private List<Floor> floorList;

    public ParkingLot() {
    }

    public ParkingLot(BigInteger id, List<Floor> floorList) {
        this.id = id;
        this.floorList = floorList;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingLot)) return false;
        ParkingLot that = (ParkingLot) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getFloorList(), that.getFloorList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFloorList());
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", floorList=" + floorList +
                '}';
    }
}
