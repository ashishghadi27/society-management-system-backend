package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.SubMember;
import com.root.sms.societyMgmtService.service.SubMemberService;
import com.root.sms.societyMgmtService.vo.SubMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submember")
public class SubMemberController {
    @Autowired
    SubMemberService subMemberService;

    @PostMapping("/{memberId}/addSubMember")
    public ResponseEntity<SubMember> add(@RequestBody SubMemberVO subMemberVO){
        SubMember subMember = subMemberService.add(subMemberVO);
        return new ResponseEntity<>(subMember, HttpStatus.CREATED);
    }
}
