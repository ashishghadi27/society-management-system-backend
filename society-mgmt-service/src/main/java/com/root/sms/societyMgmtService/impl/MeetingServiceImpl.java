package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.entity.Meeting;
import com.root.sms.societyMgmtService.repo.MeetingRepository;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.MeetingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MeetingServiceImpl {

    @Autowired
    private MeetingRepository meetingRepository;

    public ResponseEntity<GenericResponseVO> createMeeting(MeetingVO meetingVO){
        Meeting meeting = getMeetingEntity(meetingVO);
        meeting = meetingRepository.save(meeting);
        GenericResponseVO genericResponseVO = new GenericResponseVO();
        genericResponseVO.setData(meeting);
        genericResponseVO.setMessage("SUCCESS");
        return new ResponseEntity<>(genericResponseVO, HttpStatus.OK);
    }

    private Meeting getMeetingEntity(MeetingVO meetingVO) {
        Meeting meeting = new Meeting();
        meeting.setTitle(meetingVO.getTitle());
        meeting.setAgenda(meetingVO.getAgenda());
        meeting.setStartDateTime(convertToLocalDateTime(meetingVO.getStartDateTime()));
        meeting.setEndDateTime(convertToLocalDateTime(meetingVO.getEndDateTime()));
        meeting.setSid(meetingVO.getSid());
        return meeting;
    }

    private LocalDateTime convertToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

}
