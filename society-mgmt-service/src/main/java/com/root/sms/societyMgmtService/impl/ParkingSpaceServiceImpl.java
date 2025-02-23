package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.entity.ParkingSpace;
import com.root.sms.societyMgmtService.entity.Society;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.ParkingSpaceRepository;
import com.root.sms.societyMgmtService.repo.SocietyRepository;
import com.root.sms.societyMgmtService.service.ParkingSpaceService;
import com.root.sms.societyMgmtService.vo.ParkingSpaceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ParkingSpace add(ParkingSpaceVO parkingSpaceVO) {
        Society society = societyRepository.findById(parkingSpaceVO.getSocietyId()).
                orElseThrow(() -> new ResourceNotFoundException("Society", "id", parkingSpaceVO.getSocietyId()));
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setParkingId(parkingSpaceVO.getParkingId());
        parkingSpace.setSociety(society);
        parkingSpaceRepository.save(parkingSpace);
        return parkingSpace;
    }
}
