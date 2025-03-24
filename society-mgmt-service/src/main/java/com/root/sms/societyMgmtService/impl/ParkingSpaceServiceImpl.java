package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.constants.ExceptionConstants;
import com.root.sms.societyMgmtService.entity.ParkingSpace;
import com.root.sms.societyMgmtService.entity.Society;
import com.root.sms.societyMgmtService.exception.GlobalException;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.ParkingSpaceRepository;
import com.root.sms.societyMgmtService.repo.SocietyRepository;
import com.root.sms.societyMgmtService.service.ParkingSpaceService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.ParkingSpaceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final SocietyRepository societyRepository;

    @Autowired
    public ParkingSpaceServiceImpl(ParkingSpaceRepository parkingSpaceRepository,
                                   SocietyRepository societyRepository){
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.societyRepository = societyRepository;
    }
    @Override
    public ResponseEntity<GenericResponseVO> add(List<ParkingSpaceVO> parkingSpaceList) {
        Long societyId = parkingSpaceList.stream().findFirst().map(ParkingSpaceVO::getSocietyId)
                .orElseThrow(() -> new GlobalException.Builder()
                        .errorMessage(ExceptionConstants.DATA_NOT_FOUND.name()).build());
        Society society = societyRepository.findById(societyId).
                orElseThrow(() -> new ResourceNotFoundException("Society", "id", societyId));
        parkingSpaceRepository.saveAll(getParkingSpaceList(parkingSpaceList));
        GenericResponseVO genericResponseVO = new GenericResponseVO();
        genericResponseVO.setData(null);
        genericResponseVO.setMessage("SUCCESS");
        return new ResponseEntity<>(genericResponseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GenericResponseVO> getParkingSpaces(Long sid) {
        List<ParkingSpace> parkingSpaceList = parkingSpaceRepository.getUnallotedParkingSlots(sid);
        GenericResponseVO genericResponseVO = new GenericResponseVO();
        genericResponseVO.setData(parkingSpaceList);
        genericResponseVO.setMessage("SUCCESS");
        return new ResponseEntity<>(genericResponseVO, HttpStatus.OK);
    }

    private List<ParkingSpace> getParkingSpaceList(List<ParkingSpaceVO> parkingSpaceVOList){
        List<ParkingSpace> parkingSpaceList = new ArrayList<>();
        for(ParkingSpaceVO parkingSpaceVO : parkingSpaceVOList){
            ParkingSpace parkingSpace = new ParkingSpace();
            parkingSpace.setParkingId(parkingSpaceVO.getParkingId());
            parkingSpace.setSocietyId(parkingSpaceVO.getSocietyId());
            parkingSpaceList.add(parkingSpace);
        }
        return parkingSpaceList;
    }
}
