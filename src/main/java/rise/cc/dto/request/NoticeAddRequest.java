package rise.cc.dto.request;

import lombok.Getter;

import java.io.File;

@Getter
public class NoticeAddRequest {
    /*
      "noticeTitle": "공지사항 제목",
      "noticeContent": "공지사항 상세 내용",
      "commentYn": "Y",
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
    private String notificationYn;
    private String empId;
    private String originName;
    private String storedName;
    private String filePath;
    private File fileContent;

    public NoticeAddRequest(String noticeTitle, String noticeContent, String commentYn,
                  String notificationYn, String empId, String originName, String storedName,
                  String filePath, File fileContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.commentYn = commentYn;
        this.notificationYn = notificationYn;
        this.empId = empId;
        this.originName = originName == null ? "" : originName;
        this.storedName = storedName == null ? "" : storedName;
        this.filePath = filePath == null ? "" : filePath;
        this.fileContent = fileContent == null ? new File("") : fileContent;
    }
}
