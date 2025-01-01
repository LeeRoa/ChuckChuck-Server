package rise.cc.dto;

import lombok.Getter;

@Getter
public class File {
    private int fileNo;
    private String originName;
    private String storedName;
    private String createDt;
    private String updateDt;
    private int fileSize;
    private String filePath;
    private String fileContent;

    public File(String originName,
                String storedName,
                String createDt,
                String updateDt,
                int fileSize,
                String filePath,
                String fileContent) {
        this.originName = originName;
        this.storedName = storedName;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileContent = fileContent;
    }

    public static File from(NoticeAddDto dto) {
        return new File(
                dto.getOriginName(),
                dto.getStoredName(),
                dto.getCreateDt(),
                dto.getUpdateDt(),
                dto.getFileSize(),
                dto.getFilePath(),
                dto.getFileContent()
        );
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public boolean isEmptyFile() {
        return fileSize == 0;
    }
}
