package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
}
