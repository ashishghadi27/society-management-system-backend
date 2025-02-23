package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.ParkingSpaceAllotment;
import org.springframework.stereotype.Service;

@Service
public interface ParkingSpaceAllotmentService {
    ParkingSpaceAllotment add(Long memberId, Long parkingSpaceId);
}
