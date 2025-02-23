package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.entity.SubMember;
import com.root.sms.societyMgmtService.vo.SubMemberVO;
import org.springframework.stereotype.Service;

@Service
public interface SubMemberService {
    SubMember add(SubMemberVO subMemberVO);
}
