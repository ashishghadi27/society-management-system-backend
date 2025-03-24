package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.impl.MeetingServiceImpl;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.MeetingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {

    @Autowired
    private MeetingServiceImpl meetingService;

    @PostMapping("/create")
    public ResponseEntity<GenericResponseVO> createMeeting(@RequestBody MeetingVO meetingVO){
        return meetingService.createMeeting(meetingVO);
    }

}
