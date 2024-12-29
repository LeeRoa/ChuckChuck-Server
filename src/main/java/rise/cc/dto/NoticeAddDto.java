package rise.cc.dto;

import lombok.Getter;
import rise.cc.dto.request.NoticeAddRequest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
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
        File file = request.getFileContent();
        String fileContent = "";
        try (FileReader fr = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];
            fr.read(chars);

            fileContent = new String(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = format.format(date);

        return new NoticeAddDto(request.getNoticeTitle(), request.getNoticeContent(),
                request.getCommentYn(), currentDateTime, request.getUpdateDt(),
                request.getNotificationYn(), request.getEmpId(), request.getOriginName(),
                request.getStoredName(), request.getFilePath(), fileContent);
    }
}
