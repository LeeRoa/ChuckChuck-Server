package rise.cc.dto;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Notice {

    private int noticeNo;
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

    public Notice(String noticeTitle, String noticeContent, String commentYn,
                  String createDt, String updateDt, String notificationYn,
                  String empId, String originName, String storedName,
                  int fileSize, String filePath, String fileContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.commentYn = commentYn;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.notificationYn = notificationYn;
        this.empId = empId;
        this.originName = originName;
        this.storedName = storedName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileContent = fileContent;
    }

    public static Notice from(NoticeAddDto dto) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = format.format(date);

        return new Notice(
                dto.getNoticeTitle(), dto.getNoticeContent(),
                dto.getCommentYn(), currentDateTime, "",
                dto.getNotificationYn(), dto.getEmpId(), dto.getOriginName(),
                dto.getStoredName(), dto.getFileSize(), dto.getFilePath(), dto.getFileContent()
        );
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }
}
