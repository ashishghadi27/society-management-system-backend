package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.service.RoomService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.RoomRequest;
import com.root.sms.societyMgmtService.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/addRooms")
    public ResponseEntity<GenericResponseVO> add(@RequestBody RoomRequest roomRequest){
        List<Room> rooms = roomService.add(roomRequest.getRooms());
        return new ResponseEntity<>(new GenericResponseVO("SUCCESS", rooms), HttpStatus.CREATED);
    }

    @GetMapping("/getRooms")
    public ResponseEntity<GenericResponseVO> getRooms(@RequestParam("societyId") Long sid){
        List<Room> rooms = roomService.getRooms(sid);
        GenericResponseVO genericResponseVO = new GenericResponseVO();
        genericResponseVO.setData(rooms);
        genericResponseVO.setMessage("SUCCESS");
        return new ResponseEntity<>(genericResponseVO, HttpStatus.OK);
    }
}
