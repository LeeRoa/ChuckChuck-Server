package rise.cc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rise.cc.controller.request.NoticeAddRequest;
import rise.cc.dto.NoticeAddDto;
import rise.cc.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    @PostMapping
    public void postNotice(NoticeAddRequest request) {
        service.addNotice(NoticeAddDto.from(request));
    }
}
