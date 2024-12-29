package rise.cc.dto;

import lombok.Getter;

@Getter
public class FilesType {
    private int typeId;
    private int fileNo;
    private String typeInfo;
    private int docId;

    public FilesType(int fileNo, String typeInfo, int docId) {
        this.fileNo = fileNo;
        this.typeInfo = typeInfo;
        this.docId = docId;
    }

    public static FilesType ofNotice(int fileNo, int docId) {
        return new FilesType(fileNo, "B", docId);
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
