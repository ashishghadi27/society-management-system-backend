package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.MemberRepository;
import com.root.sms.societyMgmtService.repo.RoomRepository;
import com.root.sms.societyMgmtService.service.MemberService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             RoomRepository roomRepository){
        this.memberRepository = memberRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Member add(MemberVO memberVO) {
        Room room = roomRepository.findById(memberVO.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room","id",memberVO.getRoomId()));
        Member member = new Member();
        member.setFirstName(memberVO.getFirstName());
        member.setLastName(memberVO.getLastName());
        member.setEmail(memberVO.getEmail());
        member.setHashedPassword(memberVO.getHashedPassword());
        member.setType(memberVO.getType());
        member.setRid(room.getRid());
        memberRepository.save(member);
        return member;
    }

    @Override
    public ResponseEntity<GenericResponseVO> getMembersWithNoParking(Long societyId) {
        List<Member> members = memberRepository.getMembersWithNoParking(societyId);
        GenericResponseVO genericResponseVO = new GenericResponseVO();
        genericResponseVO.setData(members);
        genericResponseVO.setMessage("SUCCESS");
        return new ResponseEntity<>(genericResponseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GenericResponseVO> getMembers(Long societyId) {
        List<Member> members = memberRepository.getMembersBySociety(societyId);
        GenericResponseVO genericResponseVO = new GenericResponseVO();
        genericResponseVO.setData(members);
        genericResponseVO.setMessage("SUCCESS");
        return new ResponseEntity<>(genericResponseVO, HttpStatus.OK);
    }
}
