package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.Member;
import com.root.sms.societyMgmtService.service.MemberService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getMembersWithNoParking")
    public ResponseEntity<GenericResponseVO> getMembersWithNoParking(@RequestParam("societyId") Long societyId){
        return memberService.getMembersWithNoParking(societyId);
    }

    @GetMapping("/getMembers")
    public ResponseEntity<GenericResponseVO> getMembers(@RequestParam("societyId") Long societyId){
        return memberService.getMembers(societyId);
    }

}
