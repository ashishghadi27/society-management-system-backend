package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.ParkingSpace;
import com.root.sms.societyMgmtService.service.ParkingSpaceService;
import com.root.sms.societyMgmtService.vo.ParkingSpaceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingspace")
public class ParkingSpaceController {
    @Autowired
    ParkingSpaceService parkingSpaceService;

    @PostMapping("/addParkingSpaces")
    public ResponseEntity<ParkingSpace> add(@RequestBody ParkingSpaceVO parkingSpaceVO){
        ParkingSpace parkingSpace = parkingSpaceService.add(parkingSpaceVO);
        return new ResponseEntity<>(parkingSpace, HttpStatus.CREATED);
    }
}
