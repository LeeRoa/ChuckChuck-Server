package rise.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rise.cc.common.ResultCode;
import rise.cc.dto.schedule.CreateScheduleDTO;
import rise.cc.dto.schedule.ScheduleBaseDTO;
import rise.cc.dto.schedule.ScheduleGroup;
import rise.cc.service.ScheduleService;
import rise.cc.util.JsonUtils;

import java.util.List;
import java.util.Objects;

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

    @PostMapping("/create")
    public String createSchedule(@Validated @RequestBody CreateScheduleDTO schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append(". ");
            }
            return JsonUtils.resultJsonString(ResultCode.ERROR, errorMessage.toString());
        }

        return scheduleService.createSchedule(schedule);
    }

    @PostMapping("/invite-member")
    public String inviteMember(@RequestBody List<ScheduleGroup> scheduleGroupList) {
        return scheduleService.inviteGroupMembers(scheduleGroupList);
    }

    @GetMapping("")
    public String selectSchedule(@RequestBody List<ScheduleBaseDTO> scheduleList) throws JsonProcessingException {
        return scheduleService.selectSchedule(scheduleList);
    }

}
