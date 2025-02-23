package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.service.MemberService;
import com.root.sms.societyMgmtService.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/addMember")
    public ResponseEntity<Member> add(@RequestBody MemberVO memberVO){
        Member member = memberService.add(memberVO);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
}
