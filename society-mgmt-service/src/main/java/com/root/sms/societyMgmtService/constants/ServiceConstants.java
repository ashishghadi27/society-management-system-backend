package com.root.sms.societyMgmtService.constants;

import java.nio.file.Path;

public final class ServiceConstants {

    public static final String BASE_PATH = "D:\\Ashish\\JavaBackendProjects\\SocietyManagementSystem\\SocietyDocuments";
    public static final String societyProfileImageDownloadPath = Path.of(BASE_PATH, "profile-images").toString();
    public static final String societyFilesDownloadPath = Path.of(BASE_PATH, "society").toString();
    public static final String societyMeetingFilesDownloadPath = Path.of(BASE_PATH, "meeting").toString();

}
