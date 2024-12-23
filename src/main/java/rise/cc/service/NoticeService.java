package rise.cc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.dao.NoticeDao;
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
            int docId = dao.insertNotice(Notice.from(dto));
            int fileNo = dao.insertFile(Notice.from(dto));
            dao.insertFileType(fileNo, docId);
    }
}
