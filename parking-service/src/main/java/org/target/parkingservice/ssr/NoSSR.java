package org.target.parkingservice.ssr;

import org.target.parkingservice.dto.parkingstructure.Floor;
import org.target.parkingservice.dto.parkingstructure.ParkingSpace;
import org.target.parkingservice.dto.request.ParkingSpaceDTO;
import org.target.parkingservice.model.parkingprocess.Parking;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import static org.target.parkingservice.util.ParkingHelper.*;

public class NoSSR implements ParkingSSR {
    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors, BigInteger parkingLotId) {
        for (Floor floor : floors) {
            List<Parking> parkingsOnFloor=getAllParkingOnFloor(parkings,floor.getFloorNumber());
            Set<ParkingSpace> allParkingSpaceFromParking = getAllParkingSpaceFromParking(parkingsOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            Set<ParkingSpace> allParkingSpacesPerFloorInOrderOfEase = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor);
            for (ParkingSpace freeParkingSpots :
                    allParkingSpacesPerFloorInOrderOfEase) {
                if (!parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpots)) {
                    ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
                    parkingSpaceDTO.setFloorNumber(floor.getFloorNumber());
                    parkingSpaceDTO.setParkingLevel(freeParkingSpots.getParkingLevel());
                    parkingSpaceDTO.setParkingNumber(freeParkingSpots.getParkingNumber());
                    return parkingSpaceDTO;
                }
            }
        }
        return null;
    }



}
