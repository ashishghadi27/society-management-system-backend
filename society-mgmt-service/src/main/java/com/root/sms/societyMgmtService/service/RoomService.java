package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.vo.RoomVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<Room> add(List<RoomVO> roomList);
    List<Room> getRooms(Long sid);
}
