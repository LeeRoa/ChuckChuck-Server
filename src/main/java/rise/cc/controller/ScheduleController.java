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
import rise.cc.dto.schedule.validation.ScheduleCreateVDTO;
import rise.cc.dto.schedule.ScheduleDTO;
import rise.cc.dto.schedule.validation.ScheduleGroupCreateVDTO;
import rise.cc.dto.schedule.validation.ScheduleGroupMemberAddVDTO;
import rise.cc.dto.schedule.validation.ScheduleSearchVDTO;
import rise.cc.service.ScheduleService;
import rise.cc.util.JsonUtils;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/group-create")
    public String scheduleGroupCreate(@Validated @RequestBody ScheduleGroupCreateVDTO schedule, BindingResult bindingResult) {
        String validationError = validation(bindingResult);
        if(validationError != null) {
            return validationError;
        }
        return scheduleService.createScheduleGroup(schedule);
    }

    @PostMapping("/create")
    public String scheduleCreate(@Validated @RequestBody ScheduleCreateVDTO schedule, BindingResult bindingResult) {
        String validationError = validation(bindingResult);
        if(validationError != null) {
            return validationError;
        }
        return scheduleService.createSchedule(schedule);
    }

    @PostMapping("/invite-member")
    public String scheduleGroupMemberAdd(@Valid @RequestBody List<ScheduleGroupMemberAddVDTO> scheduleList, BindingResult bindingResult) {
        String validationError = validation(bindingResult);
        if(validationError != null) {
            return validationError;
        }
        return scheduleService.scheduleGroupMemberAdd(scheduleList);
    }

    @GetMapping("")
    public String scheduleSearch(@Valid @RequestBody List<ScheduleSearchVDTO> scheduleList, BindingResult bindingResult) throws JsonProcessingException {
        String validationError = validation(bindingResult);
        if(validationError != null) {
            return validationError;
        }
        return scheduleService.selectSchedule(scheduleList);
    }

    public String validation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append(". ");
            }
            log.info("validation error: {}", errorMessage);
            return JsonUtils.resultJsonString(ResultCode.ERROR, errorMessage.toString());
        }
        return null;
    }

}
