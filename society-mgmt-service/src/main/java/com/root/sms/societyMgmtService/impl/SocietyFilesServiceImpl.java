package com.root.sms.societyMgmtService.impl;

import com.root.sms.societyMgmtService.constants.ExceptionConstants;
import com.root.sms.societyMgmtService.constants.FileCategory;
import com.root.sms.societyMgmtService.constants.LoggingConstants;
import com.root.sms.societyMgmtService.constants.ServiceConstants;
import com.root.sms.societyMgmtService.exception.GlobalException;
import com.root.sms.societyMgmtService.helper.FileStorageHelper;
import com.root.sms.societyMgmtService.repo.SocietyFilesRepository;
import com.root.sms.societyMgmtService.repo.SocietyRepository;
import com.root.sms.societyMgmtService.service.SocietyFilesService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocietyFilesServiceImpl implements SocietyFilesService {

    private final SocietyRepository societyRepository;

    private final SocietyFilesRepository societyFilesRepository;

    private final FileStorageHelper storageHelper;

    @Value("${file.upload-society-dir}")
    private String uploadSocietyDir;

    @Value("${file.upload-meeting-dir}")
    private String uploadMeetingDir;

    @Value("${file.upload-profile-dir}")
    private String uploadProfileDir;

    @Override
    public ResponseEntity<GenericResponseVO> uploadProfileImage(MultipartFile file) {
        String methodName = "uploadProfileImage";
        try{
            return storageHelper.storeFile(file, uploadProfileDir, ServiceConstants.societyProfileImageDownloadPath);
        }
        catch (GlobalException e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), "");
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

    @Override
    public ResponseEntity<GenericResponseVO> uploadFile(MultipartFile file) {
        String methodName = "uploadFile";
        try{
            return storageHelper.storeFile(file, uploadSocietyDir, ServiceConstants.societyFilesDownloadPath);
        }
        catch (GlobalException e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), "");
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

    @Override
    public ResponseEntity<Resource> downloadFile(String fileName, String fileCategory, HttpServletRequest request) {
        String storagePath
                = (FileCategory.PROFILE_IMAGE.name().equals(fileCategory))
                ? ServiceConstants.societyProfileImageDownloadPath
                : (FileCategory.SOCIETY_FILE.name().equals(fileCategory))
                    ? ServiceConstants.societyFilesDownloadPath : ServiceConstants.societyMeetingFilesDownloadPath;
        Path filePath = Paths.get(storagePath).resolve(fileName).normalize();
        //Resource resource = storageHelper.loadFileAsResource(fileName, storagePath);
        String contentType = null;
        try {
            File file = filePath.toFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            contentType = request.getServletContext().getMimeType(file.getAbsolutePath());
            //InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .header(HttpHeaders.TRANSFER_ENCODING, "chunked")
                    .body(resource);
        } catch (IOException ex) {
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.RESOURCE_NOT_FOUND.name())
                    .description(ExceptionConstants.RESOURCE_NOT_FOUND.description).build();
        }

    }


}
