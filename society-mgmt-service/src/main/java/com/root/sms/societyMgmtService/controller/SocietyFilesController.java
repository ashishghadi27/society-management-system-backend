package com.root.sms.societyMgmtService.controller;

import com.root.sms.societyMgmtService.entity.SocietyFile;
import com.root.sms.societyMgmtService.service.SocietyFilesService;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class SocietyFilesController {

    private final SocietyFilesService societyFilesService;

    @PostMapping("/upload")
    public ResponseEntity<GenericResponseVO> uploadFile(@RequestPart("file") MultipartFile file){
        System.out.println("UPLOAD REQUEST RECEIVED");
        return societyFilesService.uploadFile(file);
    }

    @PostMapping("/upload-profile")
    public ResponseEntity<GenericResponseVO> uploadProfilePic(@RequestPart("file") MultipartFile file){
        return societyFilesService.uploadProfileImage(file);
    }

    @GetMapping("/download")
    public CompletableFuture<ResponseEntity<Resource>> downloadFile(@RequestParam String fileName,
                                                                    @RequestParam String fileCategory, HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> societyFilesService.downloadFile(fileName, fileCategory, request));
    }

}
