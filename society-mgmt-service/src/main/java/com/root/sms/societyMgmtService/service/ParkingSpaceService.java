package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.ParkingSpace;
import com.root.sms.societyMgmtService.vo.ParkingSpaceVO;
import org.springframework.stereotype.Service;

@Service
public interface ParkingSpaceService {
    ParkingSpace add(ParkingSpaceVO parkingSpaceVO);
}
