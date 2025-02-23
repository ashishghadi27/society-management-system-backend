package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.service.RoomService;
import com.root.sms.societyMgmtService.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/addRoom")
    public ResponseEntity<Room> add(@RequestBody RoomVO roomVO){
        Room room = roomService.add(roomVO);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }
}
