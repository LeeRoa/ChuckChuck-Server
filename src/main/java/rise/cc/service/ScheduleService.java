package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import rise.cc.dto.schedule.ScheduleDTO;

import java.util.List;


public interface ScheduleService {
    // 일정 그룹 관련
    String createScheduleGroup(ScheduleDTO Schedule);
    String scheduleGroupMemberAdd(List<? extends ScheduleDTO> ScheduleList);

    // 일정 관련
    String createSchedule(ScheduleDTO schedule);
    
    // 일정 조회
    String selectSchedule(ScheduleDTO scheduleList) throws JsonProcessingException;
    
    // 일정 삭제
    String deleteSchedule(ScheduleDTO scheduleList);

    String deleteGroup(ScheduleDTO scheduleList);
}
