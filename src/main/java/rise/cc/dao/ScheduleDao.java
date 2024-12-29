package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import rise.cc.dto.ScheduleGroup;

@Mapper
public interface ScheduleDao {
    Integer createScheduleGroup(ScheduleGroup scheduleGroup) throws DataAccessException;
}
