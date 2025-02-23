package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.ParkingSpaceAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceAllotmentRepository extends JpaRepository<ParkingSpaceAllotment, Long> {
}
