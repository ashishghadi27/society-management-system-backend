package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.service.SocietyService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.SocietyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/societies")
@RequiredArgsConstructor
public class SocietyController {

    private final SocietyService societyService;

    @GetMapping("/get")
    public List<SocietyVO> getSocietiesList(@RequestParam(required = false) String name) {
        return societyService.getSocietiesList(name);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponseVO> register(@RequestBody SocietyVO societyVO) {
        return new ResponseEntity<>(societyService.register(societyVO), HttpStatus.CREATED);
    }

    @PutMapping("/approve")
    public ResponseEntity<SocietyVO> approve(@RequestBody SocietyVO societyVO) {
        return new ResponseEntity<>(societyService.approve(societyVO), HttpStatus.OK);
    }


}
