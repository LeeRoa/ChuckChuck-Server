package rise.cc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rise.cc.dto.ScheduleGroup;
import rise.cc.service.ScheduleService;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/group-create")
    public String createGroup(@RequestBody ScheduleGroup scheduleGroup) {

        return scheduleService.createScheduleGroup(scheduleGroup);
    }

}
