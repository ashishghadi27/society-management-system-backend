package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.constants.ExceptionConstants;
import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.entity.Society;
import com.root.sms.societyMgmtService.exception.GlobalException;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.RoomRepository;
import com.root.sms.societyMgmtService.repo.SocietyRepository;
import com.root.sms.societyMgmtService.service.RoomService;
import com.root.sms.societyMgmtService.vo.RoomVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final SocietyRepository societyRepository;

    @Override
    public List<Room> add(List<RoomVO> roomVOList) {
        RoomVO roomVO = roomVOList.stream().findFirst()
                .orElseThrow(() -> new GlobalException.Builder()
                        .errorMessage(ExceptionConstants.INVALID_REQUEST.name()).build());
        Society society = societyRepository.findById(roomVO.getSocietyId())
                .orElseThrow(() -> new ResourceNotFoundException("Society", "id", roomVO.getSocietyId()));
        if(society != null){
            List<Room> roomList = getRoomList(roomVOList, society.getSid());
            return roomRepository.saveAll(roomList);
        }
        throw new GlobalException.Builder()
                .errorMessage(ExceptionConstants.INTERNAL_ERROR.name()).build();
    }

    @Override
    public List<Room> getRooms(Long sid) {
        return roomRepository.findBySocietyId(sid);
    }

    private List<Room> getRoomList(List<RoomVO> roomVOList, Long societyId){
        List<Room> roomList = new ArrayList<>();
        for (RoomVO roomVO : roomVOList){
            Room room = new Room();
            room.setRoomNo(roomVO.getRoomNo());
            room.setRoomSize(roomVO.getRoomSize());
            room.setSocietyId(societyId);
            roomList.add(room);
        }
        return roomList;
    }
}
