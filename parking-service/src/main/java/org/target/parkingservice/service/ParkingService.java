package org.target.parkingservice.service;

import org.target.parkingservice.dto.request.ParkingRequest;
import org.target.parkingservice.dto.request.VehicleDTO;
import org.target.parkingservice.exception.InvalidSSRException;
import org.target.parkingservice.exception.ParkingFullException;
import org.target.parkingservice.model.parkingprocess.Parking;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ParkingService {
    List<Parking> findAll();

    Parking findById(BigInteger parkingId);

    Parking save(Parking parkingDetails);

    void delete(Parking parking);

    Parking createParking(ParkingRequest parkingRequest) throws InvalidSSRException, ParkingFullException;

    Parking freeUpParking(VehicleDTO vehicleDTO);
}
