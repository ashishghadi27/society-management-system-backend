package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.entity.SubMember;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.MemberRepository;
import com.root.sms.societyMgmtService.repo.SubMemberRepository;
import com.root.sms.societyMgmtService.service.SubMemberService;
import com.root.sms.societyMgmtService.vo.SubMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubMemberServiceImpl implements SubMemberService {
    private final SubMemberRepository subMemberRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public SubMemberServiceImpl(SubMemberRepository subMemberRepository,
                                MemberRepository memberRepository){
        this.subMemberRepository = subMemberRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public SubMember add(SubMemberVO subMemberVO) {
        Member member = memberRepository.findById(subMemberVO.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", subMemberVO.getMemberId()));
        SubMember subMember = new SubMember();
        subMember.setFirstName(subMemberVO.getFirstName());
        subMember.setLastName(subMemberVO.getLastName());
        subMember.setType(subMemberVO.getType());
        subMember.setMember(member);
        subMemberRepository.save(subMember);
        return subMember;
    }
}
