package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.entity.Society;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.RoomRepository;
import com.root.sms.societyMgmtService.repo.SocietyRepository;
import com.root.sms.societyMgmtService.service.RoomService;
import com.root.sms.societyMgmtService.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final SocietyRepository societyRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository,
                           SocietyRepository societyRepository){
        this.roomRepository = roomRepository;
        this.societyRepository = societyRepository;
    }
    @Override
    public Room add(RoomVO roomVO) {
        Society society = societyRepository.findById(roomVO.getSocietyId())
                .orElseThrow(() -> new ResourceNotFoundException("Society", "id", roomVO.getSocietyId()));
        Room room = new Room();
        room.setRoomNo(roomVO.getRoomNo());
        room.setRoomSize(roomVO.getRoomSize());
        room.setSociety(society);
        roomRepository.save(room);
        return room;
    }
}
