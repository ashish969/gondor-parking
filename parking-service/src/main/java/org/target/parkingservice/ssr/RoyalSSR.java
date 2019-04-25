package org.target.parkingservice.ssr;

import org.target.parkingservice.dto.parkingstructure.Floor;
import org.target.parkingservice.dto.parkingstructure.ParkingSpace;
import org.target.parkingservice.dto.request.ParkingSpaceDTO;
import org.target.parkingservice.model.parkingprocess.Parking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.target.parkingservice.util.ParkingHelper.*;

public class RoyalSSR implements ParkingSSR {

    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors) {
        for (Floor floor : floors) {
            List<Parking> parkingsOnFloor = getAllParkingOnFloor(parkings, floor.getFloorNumber());
            Set<ParkingSpace> allParkingSpaceFromParking = getAllParkingSpaceFromParking(parkingsOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            Set<ParkingSpace> allParkingSpacesPerFloorInOrderOfEase = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor);
            List<ParkingSpace> allParkingSpacesPerFloorInOrderOfEaseList = new LinkedList(allParkingSpacesPerFloorInOrderOfEase);
            for (int i = 0; i < allParkingSpacesPerFloorInOrderOfEaseList.size(); i++) {
                ParkingSpace freeParkingSpots = allParkingSpacesPerFloorInOrderOfEaseList.get(i);
                if (!parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpots)) {
                    ParkingSpace freeParkingSpotCurrentRemaining = getParkingSpace(freeParkingSpots.getParkingNumber(),freeParkingSpots.getParkingLevel().equals(0) ? 1 : 0);
                    ParkingSpace freeParkingSpotsPrevUp =getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) - 1),1);
                    ParkingSpace freeParkingSpotsPrevDown = getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) - 1),0);
                    ParkingSpace freeParkingSpotsNextUp = getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) + 1),1);
                    ParkingSpace freeParkingSpotsNextDown = getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) + 1),0);
                    if (checkForRoyal(allParkingSpaceFromParking, freeParkingSpotsPrevUp, freeParkingSpotsPrevDown, freeParkingSpotsNextUp, freeParkingSpotsNextDown, freeParkingSpotCurrentRemaining)) {
                        return getParkingSpaceDTO(freeParkingSpots,floor.getFloorNumber());
                    }
                }
            }
        }
        return null;
    }
}
