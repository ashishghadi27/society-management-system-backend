package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.SocietyVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocietyService {
    List<SocietyVO> getSocietiesList(Long id);

    GenericResponseVO register(SocietyVO societyVO);

    SocietyVO approve(SocietyVO societyVO);
}
