package rise.cc.service;

import rise.cc.dto.Schedule;
import rise.cc.dto.ScheduleGroup;

import java.util.List;


public interface ScheduleService {
    String createScheduleGroup(ScheduleGroup scheduleGroup);
    String createSchedule(Schedule schedule);
    String inviteGroupMembers(List<ScheduleGroup> scheduleGroup);
}
