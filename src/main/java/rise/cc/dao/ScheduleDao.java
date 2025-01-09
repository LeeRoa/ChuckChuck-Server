package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import rise.cc.dto.schedule.ScheduleDTO;

import java.util.List;

@Mapper
public interface ScheduleDao {
    Integer scheduleGroupCreate(ScheduleDTO scheduleGroup) throws DataAccessException;
    Integer scheduleCreate(ScheduleDTO schedule) throws DataAccessException;
    Integer scheduleGroupMemberAdd(List<? extends ScheduleDTO> scheduleGroupList) throws DataAccessException;
    List<ScheduleDTO> scheduleSearch(ScheduleDTO schedule) throws DataAccessException;
    Integer createScheduleEmployees(ScheduleDTO schedule) throws DataAccessException;
}
