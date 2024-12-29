package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import rise.cc.dto.File;
import rise.cc.dto.FilesType;
import rise.cc.dto.Notice;

@Mapper
public interface NoticeDao {
    int insertNotice(Notice notice);
    int insertFile(File file);
    int insertFileType(FilesType fileType);
}
