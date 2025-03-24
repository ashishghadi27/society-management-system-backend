package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM parking_spaces ps " +
            "where sid = :sid and ps.pid NOT IN (SELECT parking_space_allotment.pid from parking_space_allotment)")
    List<ParkingSpace> getUnallotedParkingSlots(@Param("sid") Long societyId);
}
