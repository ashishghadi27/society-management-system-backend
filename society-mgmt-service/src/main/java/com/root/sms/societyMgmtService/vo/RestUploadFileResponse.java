package com.root.sms.societyMgmtService.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestUploadFileResponse {
	
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
	
}
