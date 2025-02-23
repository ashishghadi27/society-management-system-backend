package com.root.sms.societyMgmtService.service;

import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


public interface SocietyFilesService {
    ResponseEntity<GenericResponseVO> uploadProfileImage(MultipartFile file);
    ResponseEntity<GenericResponseVO> uploadFile(MultipartFile file);
    ResponseEntity<Resource> downloadFile(@PathVariable String fileName, String fileCategory,
                                          HttpServletRequest request);
}
