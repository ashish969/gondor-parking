package org.target.parkingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.target.parkingservice.dto.parkingstructure.Floor;
import org.target.parkingservice.dto.parkingstructure.ParkingSpace;
import org.target.parkingservice.dto.request.ParkingRequest;
import org.target.parkingservice.dto.request.ParkingSpaceDTO;
import org.target.parkingservice.dto.request.SSR;
import org.target.parkingservice.dto.request.VehicleDTO;
import org.target.parkingservice.exception.InvalidSSRException;
import org.target.parkingservice.exception.ParkingFullException;
import org.target.parkingservice.model.object.Vehicle;
import org.target.parkingservice.model.parkingprocess.Parking;
import org.target.parkingservice.proxy.ParkingLotProxy;
import org.target.parkingservice.repository.ParkingRepository;
import org.target.parkingservice.repository.VehicleRepository;
import org.target.parkingservice.ssr.*;

import java.math.BigInteger;
import java.util.*;

import static org.target.parkingservice.util.ParkingHelper.getParkingSpace;
import static org.target.parkingservice.util.ParkingHelper.getParkingSpaceDTO;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private
    ParkingRepository parkingRepository;
    @Autowired
    private
    ParkingLotProxy parkingLotProxy;
    @Autowired
    private
    VehicleRepository vehicleRepository;

    @Override
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking findById(BigInteger parkingId) {
        return parkingRepository.findById(parkingId)
                .orElseThrow(() -> new ResourceNotFoundException(parkingId.toString()));
    }

    @Override
    public Parking save(Parking parkingDetails) {
        return parkingRepository.save(parkingDetails);
    }

    @Override
    public void delete(Parking parking) {
        parkingRepository.delete(parking);
    }

    @Override
    public Parking createParking(ParkingRequest parkingRequest) throws InvalidSSRException, ParkingFullException {
        String ssr = parkingRequest.getSsr();
        List<Floor> floors = parkingLotProxy.getParkingLotById(parkingRequest.getParkingLotId()).getFloorList();
        Optional<List<Parking>> parkings = parkingRepository.findByParkingLotIdAndEndTimeGreaterThan(parkingRequest.getParkingLotId(), new Date().getTime());
        ParkingSSR parkingSSR = SSRFactory.getParkingSSR(ssr);
        ParkingSpaceDTO parkingSpaceDTO = parkingSSR.findNearestSlot(parkings.orElse(new ArrayList<>()), floors);
        if (parkingSpaceDTO == null) {
            throw new ParkingFullException("No Parking Found");
        }
        if (parkingSSR instanceof RoyalSSR) {
            ParkingSpace freeParkingSpotCurrentRemaining = getParkingSpace(parkingSpaceDTO.getParkingNumber(), parkingSpaceDTO.getParkingLevel().equals(0) ? 1 : 0);
            ParkingSpaceDTO freeParkingSpotCurrentRemainingDTO = getParkingSpaceDTO(freeParkingSpotCurrentRemaining, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotCurrentRemainingDTO, null, parkingRequest.getSsr(), parkingRequest.getParkingLotId()));

            ParkingSpace freeParkingSpotsPrevUp = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) - 1), 1);
            ParkingSpaceDTO freeParkingSpotsPrevUpDTO = getParkingSpaceDTO(freeParkingSpotsPrevUp, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsPrevUpDTO, null, parkingRequest.getSsr(), parkingRequest.getParkingLotId()));

            ParkingSpace freeParkingSpotsPrevDown = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) - 1), 0);
            ParkingSpaceDTO freeParkingSpotsPrevDownDTO = getParkingSpaceDTO(freeParkingSpotsPrevDown, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsPrevDownDTO, null, parkingRequest.getSsr(), parkingRequest.getParkingLotId()));

            ParkingSpace freeParkingSpotsNextUp = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) + 1), 1);
            ParkingSpaceDTO freeParkingSpotsNextUpDTO = getParkingSpaceDTO(freeParkingSpotsNextUp, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsNextUpDTO, null, parkingRequest.getSsr(), parkingRequest.getParkingLotId()));

            ParkingSpace freeParkingSpotsNextDown = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) + 1), 0);
            ParkingSpaceDTO freeParkingSpotsNextDownDTO = getParkingSpaceDTO(freeParkingSpotsNextDown, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsNextDownDTO, null, parkingRequest.getSsr(), parkingRequest.getParkingLotId()));

        }
        return save(getParkingFromDTO(parkingSpaceDTO, parkingRequest.getVehicle(), parkingRequest.getSsr(), parkingRequest.getParkingLotId()));
    }

    @Override
    public Parking freeUpParking(VehicleDTO vehicleDTO) {
        Vehicle topByVehicleNumberPlateAndVehicleTypeOrderByIdDesc = vehicleRepository.findTopByVehicleNumberPlateAndVehicleTypeOrderByIdDesc(vehicleDTO.getVehicleNumberPlate(), vehicleDTO.getVehicleType()).get();
        Optional<Parking> parking = parkingRepository.findByVehicle(topByVehicleNumberPlateAndVehicleTypeOrderByIdDesc);
        parking.get().setEndTime(new Date().getTime());
        if (SSR.ROYAL.name().equals(parking.get().getSsr())) {
            Optional<Parking> parking1 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndSsr(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), parking.get().getParkingNumber(), parking.get().getParkingLevel().equals(0) ? 1 : 0, parking.get().getSsr());
            Optional<Parking> parking2 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndSsr(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) - 1), 1, parking.get().getSsr());
            Optional<Parking> parking3 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndSsr(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) - 1), 0, parking.get().getSsr());
            Optional<Parking> parking4 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndSsr(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) + 1), 1, parking.get().getSsr());
            Optional<Parking> parking5 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndSsr(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) + 1), 0, parking.get().getSsr());
            parking1.get().setEndTime(new Date().getTime());
            parking2.get().setEndTime(new Date().getTime());
            parking3.get().setEndTime(new Date().getTime());
            parking4.get().setEndTime(new Date().getTime());
            parking5.get().setEndTime(new Date().getTime());

        }
        return save(parking.get());
    }

    private Parking getParkingFromDTO(ParkingSpaceDTO parkingSpaceDTO, VehicleDTO vehicleDTO, String ssr, BigInteger parkingLotId) {
        Parking parking = new Parking();
        parking.setStartTime(new Date().getTime());
        if (vehicleDTO != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleDTO.getVehicleType());
            vehicle.setVehicleNumberPlate(vehicleDTO.getVehicleNumberPlate());
            parking.setVehicle(vehicle);
        }

        parking.setSsr(ssr);
        parking.setParkingLotId(parkingLotId);
        parking.setFloorNumber(parkingSpaceDTO.getFloorNumber());
        parking.setParkingNumber(parkingSpaceDTO.getParkingNumber());
        parking.setParkingLevel(parkingSpaceDTO.getParkingLevel());
        return parking;
    }
}
