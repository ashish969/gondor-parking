package org.target.parkingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.target.parkingservice.model.object.Vehicle;
import org.target.parkingservice.model.parkingprocess.Parking;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, BigInteger> {
    Optional<List<Parking>> findByParkingLotId(BigInteger parkingLotId);
    @Query("SELECT p FROM Parking p where p.parkingLotId = :parkingLotId AND p.floorNumber = :floorNumber")
    Optional<List<Parking>> findByParkingLotIdAndFloorNumber(@Param("parkingLotId") BigInteger parkingLotId,
                                                       @Param("floorNumber") Integer floorNumber);
    @Query("SELECT p FROM Parking p where p.parkingLotId = :parkingLotId AND (p.endTime > :endTime OR p.endTime IS NULL)")
    Optional<List<Parking>> findByParkingLotIdAndEndTimeGreaterThan(BigInteger parkingLotId,
                                                                    Long endTime);
    Optional<Parking> findByVehicle(Vehicle vehicle);

    @Query("SELECT p FROM Parking p where p.parkingLotId = :parkingLotId AND p.floorNumber = :floorNumber AND p.parkingNumber = :parkingNumber AND p.parkingLevel = :parkingLevel AND p.ssr = :ssr")
    Optional<Parking> findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndSsr(@Param("parkingLotId") BigInteger parkingLotId,
                                                             @Param("floorNumber") Integer floorNumber,@Param("parkingNumber") String parkingNumber,@Param("parkingLevel") Integer parkingLevel,@Param("ssr") String ssr);

}
