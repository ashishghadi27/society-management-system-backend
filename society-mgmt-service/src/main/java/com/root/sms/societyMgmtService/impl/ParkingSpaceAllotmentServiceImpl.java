package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.entity.ParkingSpace;
import com.root.sms.societyMgmtService.entity.ParkingSpaceAllotment;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.MemberRepository;
import com.root.sms.societyMgmtService.repo.ParkingSpaceAllotmentRepository;
import com.root.sms.societyMgmtService.repo.ParkingSpaceRepository;
import com.root.sms.societyMgmtService.service.ParkingSpaceAllotmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpaceAllotmentServiceImpl implements ParkingSpaceAllotmentService {
    private final MemberRepository memberRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingSpaceAllotmentRepository parkingSpaceAllotmentRepository;

    @Autowired
    public ParkingSpaceAllotmentServiceImpl(MemberRepository memberRepository,
                                            ParkingSpaceRepository parkingSpaceRepository,
                                            ParkingSpaceAllotmentRepository parkingSpaceAllotmentRepository){
        this.memberRepository = memberRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.parkingSpaceAllotmentRepository = parkingSpaceAllotmentRepository;
    }

    @Override
    public ParkingSpaceAllotment add(Long memberId, Long parkingSpaceId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", memberId));
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(parkingSpaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Parking Space", "id", parkingSpaceId));
//        if (member.getRoom().getSociety().equals(parkingSpace.getSociety())){
//            ParkingSpaceAllotment parkingSpaceAllotment = new ParkingSpaceAllotment();
//            parkingSpaceAllotment.setMember(member);
//            parkingSpaceAllotment.setParkingSpace(parkingSpace);
//            parkingSpaceAllotmentRepository.save(parkingSpaceAllotment);
//            return parkingSpaceAllotment;
//        }
        return null;
    }
}
