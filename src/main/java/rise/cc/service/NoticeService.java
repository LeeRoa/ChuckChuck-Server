package rise.cc.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.dao.NoticeDao;
import rise.cc.dto.File;
import rise.cc.dto.FilesType;
import rise.cc.dto.NoticeAddDto;
import rise.cc.dto.Notice;

@Service
public class NoticeService {
    private final NoticeDao dao;

    public NoticeService(NoticeDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void addNotice(NoticeAddDto dto) {
            Notice notice = Notice.from(dto);
            dao.insertNotice(notice);
            int noticeNo = notice.getNoticeNo();

            File file = File.from(dto);

            dao.insertFile(file);
            int fileNo = file.getFileNo();

            dao.insertFileType(FilesType.ofNotice(fileNo, noticeNo));
    }
}
