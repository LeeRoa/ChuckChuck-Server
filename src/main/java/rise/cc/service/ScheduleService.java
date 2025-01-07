package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import rise.cc.dto.schedule.ScheduleDTO;

import java.util.List;


public interface ScheduleService {
    String createScheduleGroup(ScheduleDTO Schedule);
    String createSchedule(ScheduleDTO schedule);
    String scheduleGroupMemberAdd(List<? extends ScheduleDTO> ScheduleList);
    String selectSchedule(List<? extends ScheduleDTO> scheduleList) throws JsonProcessingException;
}
