package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.ParkingSpaceAllotment;
import com.root.sms.societyMgmtService.service.ParkingSpaceAllotmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingspaceallotment")
public class ParkingSpaceAllotmentController {
    @Autowired
    ParkingSpaceAllotmentService parkingSpaceAllotmentService;

    @PostMapping("/add")
    public ResponseEntity<ParkingSpaceAllotment> add(@RequestParam Long memberId, @RequestParam Long parkingSpaceId){
        ParkingSpaceAllotment parkingSpaceAllotment = parkingSpaceAllotmentService.add(memberId, parkingSpaceId);
        return new ResponseEntity<>(parkingSpaceAllotment, HttpStatus.CREATED);
    }
}
