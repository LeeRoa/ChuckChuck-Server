package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import rise.cc.dto.schedule.ScheduleDTO;

import java.util.List;

@Mapper
public interface ScheduleDao {
    // 일정 그룹 관련
    Integer scheduleGroupCreate(ScheduleDTO scheduleGroup) throws DataAccessException;
    Integer scheduleGroupMemberAdd(List<? extends ScheduleDTO> scheduleGroupList) throws DataAccessException;

    // 일정 관련
    Integer scheduleCreate(ScheduleDTO schedule) throws DataAccessException;
    Integer createScheduleEmployees(ScheduleDTO schedule) throws DataAccessException;

    // 일정 조회
    List<ScheduleDTO> scheduleSearch(ScheduleDTO schedule) throws DataAccessException;
    
    // 일정 삭제 관련
    Character selectScheduleCreator(ScheduleDTO schedule) throws DataAccessException;
    Integer deleteSchedule(ScheduleDTO schedule) throws DataAccessException;
    Integer deleteScheduleMember(ScheduleDTO schedule) throws DataAccessException;
}
