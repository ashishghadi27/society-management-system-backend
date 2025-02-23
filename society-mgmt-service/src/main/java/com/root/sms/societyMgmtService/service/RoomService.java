package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.vo.RoomVO;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    Room add(RoomVO room);
}
