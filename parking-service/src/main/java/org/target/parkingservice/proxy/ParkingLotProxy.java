package org.target.parkingservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.target.parkingservice.dto.parkingstructure.ParkingLot;

import java.math.BigInteger;
@RibbonClient(name="parkinglot-service")
@FeignClient(name="parkinglot-service")
public interface ParkingLotProxy {
    @GetMapping("/parkinglots/{id}")
    public ParkingLot getParkingLotById(@PathVariable(value = "id") BigInteger parkingLotId);
}
