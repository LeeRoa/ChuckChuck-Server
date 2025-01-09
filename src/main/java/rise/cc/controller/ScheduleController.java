package rise.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rise.cc.dto.schedule.validation.ScheduleCreateVDTO;
import rise.cc.dto.schedule.validation.ScheduleGroupCreateVDTO;
import rise.cc.dto.schedule.validation.ScheduleGroupMemberAddVDTO;
import rise.cc.dto.schedule.validation.ScheduleSearchVDTO;
import rise.cc.service.ScheduleService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/group-create")
    public String scheduleGroupCreate(@Validated @RequestBody ScheduleGroupCreateVDTO schedule) {
        return scheduleService.createScheduleGroup(schedule);
    }

    @PostMapping("/create")
    public String scheduleCreate(@Validated @RequestBody ScheduleCreateVDTO schedule) {
        return scheduleService.createSchedule(schedule);
    }

    @PostMapping("/invite-member")
    public String scheduleGroupMemberAdd(@Valid @RequestBody List<ScheduleGroupMemberAddVDTO> scheduleList) {
        return scheduleService.scheduleGroupMemberAdd(scheduleList);
    }

    @PostMapping("")
    public String scheduleSearch(@Valid @RequestBody ScheduleSearchVDTO schedule) throws JsonProcessingException {
        return scheduleService.selectSchedule(schedule);
    }
}
