package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.ParkingSpaceVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingSpaceService {
    ResponseEntity<GenericResponseVO> add(List<ParkingSpaceVO> parkingSpaceList);
    ResponseEntity<GenericResponseVO> getParkingSpaces(Long sid);
}
