package org.target.parkingservice.dto.parkingstructure;



import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class ParkingLot {
    private BigInteger id;
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
