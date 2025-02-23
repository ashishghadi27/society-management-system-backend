package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.entity.Room;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.MemberRepository;
import com.root.sms.societyMgmtService.repo.RoomRepository;
import com.root.sms.societyMgmtService.service.MemberService;
import com.root.sms.societyMgmtService.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        member.setRoom(room);
        memberRepository.save(member);
        return member;
    }
}
