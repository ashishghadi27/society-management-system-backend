package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.constants.ExceptionConstants;
import com.root.sms.societyMgmtService.constants.LoggingConstants;
import com.root.sms.societyMgmtService.entity.Society;
import com.root.sms.societyMgmtService.entity.SocietyFile;
import com.root.sms.societyMgmtService.exception.GlobalException;
import com.root.sms.societyMgmtService.exception.ResourceNotFoundException;
import com.root.sms.societyMgmtService.repo.SocietyFilesRepository;
import com.root.sms.societyMgmtService.repo.SocietyRepository;
import com.root.sms.societyMgmtService.service.SocietyService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.SocietyFileVO;
import com.root.sms.societyMgmtService.vo.SocietyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocietyServiceImpl implements SocietyService {

    private final SocietyRepository societyRepository;

    private final SocietyFilesRepository societyFilesRepository;

    @Override
    public List<SocietyVO> getSocietiesList(Long id) {
        List<Society> societyList;
        societyList = (id != 0) ?
                societyRepository.findBySidAndIsApprovedTrue(id) :
                societyRepository.findByIsApprovedTrue();
        List<SocietyVO> societyVOList = new ArrayList<>();
        for (Society society : societyList){
            SocietyVO societyVO = new SocietyVO();
            societyVO.setSid(society.getSid());
            societyVO.setName(society.getName());
            societyVO.setAddressLine1(society.getAddressLine1());
            societyVO.setAddressLine2(society.getAddressLine2());
            societyVO.setPlotNumber(society.getPlotNumber());
            societyVO.setProfilePic(society.getProfilePic());
            societyVO.setParkingAvailable(society.getParkingAvailable());
            societyVOList.add(societyVO);
        }
        return societyVOList;
    }

    @Override
    public GenericResponseVO register(SocietyVO societyVO) {
        String methodName = "register";
        try{
            Society society = new Society();
            society.setName(societyVO.getName());
            society.setAddressLine1(societyVO.getAddressLine1());
            society.setAddressLine2(societyVO.getAddressLine2());
            society.setPlotNumber(societyVO.getPlotNumber());
            society.setProfilePic(societyVO.getProfilePic());
            society.setSocietyFund(societyVO.getSocietyFund());
            society.setParkingAvailable(societyVO.getParkingAvailable());
            society = societyRepository.save(society);
            saveSocietyFiles(society, societyVO.getFiles());
            societyVO.setSid(society.getSid());
            return new GenericResponseVO("Data Saved", societyVO);
        }
        catch (GlobalException e) {
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), e.getDescription());
            throw e;
        }
        catch (Exception e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), "");
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INTERNAL_ERROR.name())
                    .description(ExceptionConstants.INTERNAL_ERROR.description).build();
        }
    }

    private void saveSocietyFiles(Society society, List<SocietyFileVO> societyFileList) {
        List<SocietyFile> societyFileEntityList = new ArrayList<>();
        for(SocietyFileVO fileVO : societyFileList){
            SocietyFile fileEntity = new SocietyFile();
            fileEntity.setFileName(fileVO.getFileName());
            fileEntity.setFileType(fileVO.getFileType());
            fileEntity.setLink(fileVO.getLink());
            fileEntity.setSid(society.getSid());
            societyFileEntityList.add(fileEntity);
        }
        societyFilesRepository.saveAll(societyFileEntityList);
    }

    @Override
    public SocietyVO approve(SocietyVO societyVO) {
        Society society = societyRepository.findById(societyVO.getSid())
                .orElseThrow(() -> new ResourceNotFoundException("Society", "id", societyVO.getSid()));
        society.setIsApproved(Boolean.TRUE);
        societyRepository.save(society);
        return societyVO;
    }
}
