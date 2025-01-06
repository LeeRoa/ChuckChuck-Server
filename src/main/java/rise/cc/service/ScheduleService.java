package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import rise.cc.dto.schedule.ScheduleBaseDTO;
import rise.cc.dto.schedule.ScheduleGroup;

import java.util.List;


public interface ScheduleService {
    String createScheduleGroup(ScheduleGroup scheduleGroup);
    String createSchedule(ScheduleBaseDTO schedule);
    String inviteGroupMembers(List<ScheduleGroup> scheduleGroup);
    String selectSchedule(List<ScheduleBaseDTO> scheduleList) throws JsonProcessingException;
}
