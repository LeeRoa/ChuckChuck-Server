package rise.cc.controller;

import org.springframework.web.bind.annotation.*;
import rise.cc.dto.request.NoticeAddRequest;
import rise.cc.dto.NoticeAddDto;
import rise.cc.service.NoticeService;

/**
 *  공지사항 관련 연산을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    /**
     * 사원(계정)을 조회하는 기능
     * @param request 공지사항에 포함될 내용, 첨부파일(optional)
     */
    @PostMapping
    public void postNotice(@RequestBody NoticeAddRequest request) {
        service.addNotice(NoticeAddDto.from(request));
    }
}
