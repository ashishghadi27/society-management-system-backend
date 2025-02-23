package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Member add(MemberVO memberVO);
}
