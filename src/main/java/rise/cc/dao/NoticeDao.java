package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import rise.cc.dto.Notice;

@Mapper
public interface NoticeDao {
    int insertNotice(Notice notice);
    int insertFile(Notice notice);
    int insertFileType(int fileType, int docId);
//    void addNotice(Notice notice);
}
