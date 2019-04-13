package org.target.parkinglotservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.target.parkinglotservice.model.parkingstructure.ParkingLot;
import org.target.parkinglotservice.repository.ParkingLotRepository;
import org.target.parkinglotservice.service.ParkingLotService;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/parkinglots", produces = "application/json")
public class ParkingLotController {
    @Autowired
    ParkingLotService parkingLotService;
//
//    @GetMapping
//    public ResponseEntity<Resources<ParkingLot>> getParkingLots() {
//        List<ParkingLot> parkingLotList = parkingLotService.findAll();
//        Resources<ParkingLot> parkingResources = new Resources<>(parkingLotList);
//        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
//        parkingResources.add(new Link(uriString, "self"));
//        return ResponseEntity.ok(parkingResources);
//    }


    @GetMapping
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotService.findAll();
    }

    @PostMapping
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.save(parkingLot);
    }

    @GetMapping("/{id}")
    public ParkingLot getParkingLotById(@PathVariable(value = "id") BigInteger parkingLotId) {
        return parkingLotService.findById(parkingLotId);
    }

    @PutMapping("/{id}")
    public ParkingLot updateParkingLot(@PathVariable(value = "id") BigInteger parkingLotId,
                           @Valid @RequestBody ParkingLot parkingLotDetails) {

        parkingLotService.findById(parkingLotId);

        parkingLotDetails.setId(parkingLotId);

        ParkingLot updatedParkingLot = parkingLotService.save(parkingLotDetails);
        return updatedParkingLot;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParkingLot(@PathVariable(value = "id") BigInteger parkingLotId) {
        ParkingLot parkingLot = parkingLotService.findById(parkingLotId);

        parkingLotService.delete(parkingLot);

        return ResponseEntity.ok().build();
    }

}
