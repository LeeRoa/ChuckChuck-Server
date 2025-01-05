package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import rise.cc.dto.Schedule;
import rise.cc.dto.ScheduleGroup;

import java.util.List;

@Mapper
public interface ScheduleDao {
    Integer createScheduleGroup(ScheduleGroup scheduleGroup) throws DataAccessException;
    Integer createSchedule(Schedule schedule) throws DataAccessException;
    Integer inviteGroupMembers(List<ScheduleGroup> scheduleGroupList) throws DataAccessException;
    List<Schedule> findSchedules(List<Schedule> scheduleList) throws DataAccessException;
    Integer createScheduleEmployees(Schedule schedule) throws DataAccessException;
}
