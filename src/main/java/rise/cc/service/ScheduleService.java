package rise.cc.service;

import rise.cc.dto.Schedule;
import rise.cc.dto.ScheduleGroup;


public interface ScheduleService {
    String createScheduleGroup(ScheduleGroup scheduleGroup);
    String createSchedule(Schedule schedule);
}
