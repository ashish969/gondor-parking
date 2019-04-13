package org.target.parkingservice.ssr;

import org.target.parkingservice.dto.parkingstructure.Floor;
import org.target.parkingservice.dto.request.ParkingSpaceDTO;
import org.target.parkingservice.model.parkingprocess.Parking;

import java.math.BigInteger;
import java.util.List;

public interface ParkingSSR {

    ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors, BigInteger parkingLotId);
}
