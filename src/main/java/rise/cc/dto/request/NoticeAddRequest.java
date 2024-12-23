package rise.cc.controller.request;

public class NoticeAddRequest {
    /*
      "noticeTitle": "공지사항 제목",
      "noticeContent": "공지사항 상세 내용",
      "commentYn": "Y",
      "createDt": "",
      "updateDt": "",
      "notificationYn": "Y",
      "empId": "1",

      "originName": "attach.pdf", (optional)
      "storedName": "첨부파일명", (optional)
      "filePath": "./tmp/attach.pdf", (optional)
      "fileContent", "파일_데이터_스트림값" (optional)
     */

    private String noticeTitle;
    private String noticeContent;
    private String commentYn;
    private String createDt;
    private String updateDt;
    private String notificationYn;
    private String empId;
    private String originName;
    private String storedName;
    private String filePath;
    private String fileContent;

    public NoticeAddRequest(String noticeTitle, String noticeContent, String commentYn,
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
        this.originName = originName == null ? "" : originName;
        this.storedName = storedName == null ? "" : storedName;
        this.filePath = filePath == null ? "" : filePath;
        this.fileContent = fileContent == null ? "" : fileContent;
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

    public String getFilePath() {
        return filePath;
    }

    public String getFileContent() {
        return fileContent;
    }
}
