package rise.cc.dto;

import rise.cc.controller.request.NoticeAddRequest;

public class NoticeAddDto {

    private String noticeTitle;
    private String noticeContent;
    private String commentYn;
    private String createDt;
    private String updateDt;
    private String notificationYn;
    private String empId;
    private String originName;
    private String storedName;
    private int fileSize;
    private String filePath;
    private String fileContent;

    public NoticeAddDto(String noticeTitle, String noticeContent, String commentYn,
                        String createDt, String updateDt, String notificationYn,
                        String empId, String originName, String storedName,
                        String filePath, String fileContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.commentYn = commentYn;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.notificationYn = notificationYn;
        this.empId = empId;
        this.originName = originName;
        this.storedName = storedName;
        this.fileSize = fileContent.length();
        this.filePath = filePath;
        this.fileContent = fileContent;
    }

    public static NoticeAddDto from(NoticeAddRequest request) {
        return new NoticeAddDto(request.getNoticeTitle(), request.getNoticeContent(),
                request.getCommentYn(), request.getCreateDt(), request.getUpdateDt(),
                request.getNotificationYn(), request.getEmpId(), request.getOriginName(),
                request.getStoredName(), request.getFilePath(), request.getFileContent());
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public String getCommentYn() {
        return commentYn;
    }

    public String getCreateDt() {
        return createDt;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public String getNotificationYn() {
        return notificationYn;
    }

    public String getEmpId() {
        return empId;
    }

    public String getOriginName() {
        return originName;
    }

    public String getStoredName() {
        return storedName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileContent() {
        return fileContent;
    }
}
