package org.target.parkingservice.ssr;

import org.target.parkingservice.dto.parkingstructure.Floor;
import org.target.parkingservice.dto.parkingstructure.ParkingSpace;
import org.target.parkingservice.dto.request.ParkingSpaceDTO;
import org.target.parkingservice.model.parkingprocess.Parking;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import static org.target.parkingservice.util.ParkingHelper.*;

public class ElderSSR implements ParkingSSR {

    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors, BigInteger parkingLotId) {
        for (Floor floor : floors) {
            List<Parking> parkingsOnFloor = getAllParkingOnFloor(parkings, floor.getFloorNumber());
            Set<ParkingSpace> allParkingSpaceFromParking = getAllParkingSpaceFromParking(parkingsOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            Set<ParkingSpace> allParkingSpacesPerFloorInOrderOfEase = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor);
            for (ParkingSpace freeParkingSpots :
                    allParkingSpacesPerFloorInOrderOfEase) {
                if (!parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpots)) {
                    if (freeParkingSpots.getParkingLevel().equals(0)) {
                        ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
                        parkingSpaceDTO.setFloorNumber(floor.getFloorNumber());
                        parkingSpaceDTO.setParkingLevel(freeParkingSpots.getParkingLevel());
                        parkingSpaceDTO.setParkingNumber(freeParkingSpots.getParkingNumber());
                        return parkingSpaceDTO;
                    }
                }
            }
        }
        return null;
    }
}
//    for (Floor floor : floors) {
//            List<Parking> parkingsOnFloor=getAllParkingOnFloor(parkings, floor.getFloorNumber());
//        Set<String> parkingNumberWithLevels = getAllParkingSpaceFromParking(parkingsOnFloor,parkingLotId, floor.getFloorNumber());
//        List<ParkingSpace> parkingSpaces = floor.getParkingSpaceList();
//        Set<String> freeParkingNumberWithLevels = getFreeParkingSpaceInOrderOfEase(parkingSpaces);
//        for (String freeParkingSpots :
//        freeParkingNumberWithLevels) {
//        String[] freeParking = freeParkingSpots.split("N");
//        if (!parkingNumberWithLevels.contains(freeParking[1])) {
//        String[] parkNumAndLevelArray = freeParking[1].split("L");
//        String parkNum = parkNumAndLevelArray[0];
//        Integer level = Integer.valueOf(parkNumAndLevelArray[1]);
//        if (level == 0 && parkingNumberWithLevels.contains(parkNum + "L" + 1)) {
//        ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
//        parkingSpaceDTO.setFloorNumber(floor.getFloorNumber());
//        parkingSpaceDTO.setParkingLevel(level);
//        parkingSpaceDTO.setParkingNumber(parkNum);
//        return parkingSpaceDTO;
//        }
//        }
//        }
//        }
//        return null;
/*

    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors, BigInteger parkingLotId) {
        int countOfNonExistenceOfUpperLevel = 0;
        ParkingSpaceDTO parkingSpaceDTOFirst = null;
        for (Floor floor : floors) {
            List<Parking> parkingsOnFloor = getAllParkingOnFloor(parkings, floor.getFloorNumber());
            Set<ParkingSpace> allParkingSpaceFromParking = getAllParkingSpaceFromParking(parkingsOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            Set<ParkingSpace> allParkingSpacesPerFloorInOrderOfEase = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor);
            for (ParkingSpace freeParkingSpots :
                    allParkingSpacesPerFloorInOrderOfEase) {
                if (!parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpots)) {
                    ParkingSpace elderCheckForTopLevelParkingSpace = new ParkingSpace();
                    elderCheckForTopLevelParkingSpace.setParkingLevel(1);
                    elderCheckForTopLevelParkingSpace.setParkingNumber(freeParkingSpots.getParkingNumber());
                    ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
                    parkingSpaceDTO.setFloorNumber(floor.getFloorNumber());
                    parkingSpaceDTO.setParkingLevel(freeParkingSpots.getParkingLevel());
                    parkingSpaceDTO.setParkingNumber(freeParkingSpots.getParkingNumber());
                    if (countOfNonExistenceOfUpperLevel == 0&&freeParkingSpots.getParkingLevel().equals(0)) {
                        parkingSpaceDTOFirst = parkingSpaceDTO;
                    }
                    if (freeParkingSpots.getParkingLevel().equals(0) && ((countOfNonExistenceOfUpperLevel >= 5) || parkingAlreadyExists(allParkingSpaceFromParking, elderCheckForTopLevelParkingSpace))) {
                        return countOfNonExistenceOfUpperLevel < 5 ? parkingSpaceDTO : parkingSpaceDTOFirst;
                    }
                    countOfNonExistenceOfUpperLevel++;
                }
            }
        }
        return null;
    }*/
