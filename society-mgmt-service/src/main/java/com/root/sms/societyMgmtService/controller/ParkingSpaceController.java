package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.ParkingSpace;
import com.root.sms.societyMgmtService.service.ParkingSpaceService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.ParkingSpaceRequest;
import com.root.sms.societyMgmtService.vo.ParkingSpaceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingspace")
public class ParkingSpaceController {
    @Autowired
    ParkingSpaceService parkingSpaceService;

    @PostMapping("/addParkingSpaces")
    public ResponseEntity<GenericResponseVO> add(@RequestBody ParkingSpaceRequest parkingSpaceRequest){
        return parkingSpaceService.add(parkingSpaceRequest.getParkingSpaces());
    }

    @GetMapping("/getParkingSpaces")
    public ResponseEntity<GenericResponseVO> get(@RequestParam("societyId") Long societyId){
        return parkingSpaceService.getParkingSpaces(societyId);
    }
}
