package org.target.parkinglotservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.target.parkinglotservice.model.parkingstructure.ParkingLot;
import org.target.parkinglotservice.repository.ParkingLotRepository;

import java.math.BigInteger;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository;
    @Override
    public List<ParkingLot> findAll() {
        return parkingLotRepository.findAll();
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public ParkingLot findById(BigInteger parkingLotId) {
        return parkingLotRepository.findById(parkingLotId)
                .orElseThrow(() -> new ResourceNotFoundException(parkingLotId.toString()));
    }

    @Override
    public void delete(ParkingLot parkingLot) {
        parkingLotRepository.delete(parkingLot);
    }
}
