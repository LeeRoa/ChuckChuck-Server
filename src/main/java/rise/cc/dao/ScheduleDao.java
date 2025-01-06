package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import rise.cc.dto.schedule.ScheduleBaseDTO;
import rise.cc.dto.schedule.ScheduleGroup;

import java.util.List;

@Mapper
public interface ScheduleDao {
    Integer createScheduleGroup(ScheduleGroup scheduleGroup) throws DataAccessException;
    Integer createSchedule(ScheduleBaseDTO schedule) throws DataAccessException;
    Integer inviteGroupMembers(List<ScheduleGroup> scheduleGroupList) throws DataAccessException;
    List<ScheduleBaseDTO> findSchedules(List<ScheduleBaseDTO> scheduleList) throws DataAccessException;
    Integer createScheduleEmployees(ScheduleBaseDTO schedule) throws DataAccessException;
}
