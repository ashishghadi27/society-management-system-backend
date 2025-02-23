package com.root.sms.societyMgmtService.helper;

import com.root.sms.societyMgmtService.constants.ExceptionConstants;
import com.root.sms.societyMgmtService.exception.GlobalException;
import com.root.sms.societyMgmtService.vo.GenericResponseVO;
import com.root.sms.societyMgmtService.vo.RestUploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class FileStorageHelper {

	public ResponseEntity<GenericResponseVO> storeFile(MultipartFile file, String storagePath, String downloadPath) {
		try {
			Path fileStorageLocation = Path.of(storagePath);
			String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
			Files.createDirectories(fileStorageLocation);
			if (fileName.contains("..")) {
				return new ResponseEntity<>(new GenericResponseVO("Invalid file name", null), HttpStatus.OK);
			}
			Path targetLocation = fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(downloadPath).path(fileName).toUriString();
			return new ResponseEntity<>(new GenericResponseVO("File Uploaded",
					new RestUploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize())), HttpStatus.OK);
		} catch (Exception ex) {
			throw new GlobalException.Builder()
					.errorMessage(ExceptionConstants.FILE_UPLOAD_FAILED.name())
					.description(ExceptionConstants.FILE_UPLOAD_FAILED.description).build();
		}
	}


	public Resource loadFileAsResource(String fileName, String storagePath) {
        try {
			Path fileStorageLocation = Path.of(storagePath);
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
				throw new GlobalException.Builder()
						.errorMessage(ExceptionConstants.RESOURCE_NOT_FOUND.name())
						.description(ExceptionConstants.RESOURCE_NOT_FOUND.description).build();
            }
        } catch (MalformedURLException ex) {
			throw new GlobalException.Builder()
					.errorMessage(ExceptionConstants.INTERNAL_ERROR.name())
					.description(ExceptionConstants.INTERNAL_ERROR.description).build();
        }
    }

}
