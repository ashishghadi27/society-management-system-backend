package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Member add(MemberVO memberVO);
    ResponseEntity<GenericResponseVO> getMembersWithNoParking(Long societyId);
    ResponseEntity<GenericResponseVO> getMembers(Long societyId);
}
