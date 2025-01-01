package rise.cc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import rise.cc.common.ResultCode;
import rise.cc.dao.ScheduleDao;
import rise.cc.dto.Schedule;
import rise.cc.dto.ScheduleGroup;
import rise.cc.util.JsonUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;

    @Override
    public String createScheduleGroup(ScheduleGroup scheduleGroup) {
        validateScheduleGroup(scheduleGroup);

        try {
            return processDatabaseOperation(
                    scheduleDao.createScheduleGroup(scheduleGroup),
                    "일정 그룹 생성 성공",
                    "일정 그룹 생성 실패"
            );
        } catch (DataAccessException e) {
            log.error("DB 에러 발생: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("일정 그룹 생성 중 알 수 없는 오류 발생: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public String createSchedule(Schedule schedule) {
        validateSchedule(schedule);

        try {
            return processDatabaseOperation(
                    scheduleDao.createSchedule(schedule),
                    "일정 생성 성공",
                    "일정 생성 실패"
            );
        } catch (DataAccessException e) {
            log.error("DB 에러 발생: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("일정 생성 중 알 수 없는 오류 발생: {}", e.getMessage());
            throw e;
        }
    }

    private void validateScheduleGroup(ScheduleGroup scheduleGroup) {
        if (scheduleGroup.getScheduleGroupName() == null) {
            throw new IllegalArgumentException("Schedule group name is null.");
        }
        if (scheduleGroup.getEmpId() == null) {
            throw new IllegalArgumentException("Schedule group empId is null.");
        }
    }

    private void validateSchedule(Schedule schedule) {
        if (schedule.getScheduleName() == null) {
            throw new IllegalArgumentException("Schedule name is null.");
        }
        if (schedule.getScheduleStartDt() == null) {
            throw new IllegalArgumentException("Schedule start dt is null.");
        }
        if (schedule.getScheduleEndDt() == null) {
            throw new IllegalArgumentException("Schedule end dt is null.");
        }
        if (schedule.getScheduleGroupId() == null) {
            throw new IllegalArgumentException("Schedule group id is null.");
        }
    }

    private String processDatabaseOperation(int result, String successMessage, String errorMessage) {
        if (result > 0) {
            log.info(successMessage);
            return JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
        } else {
            log.error(errorMessage);
            return JsonUtils.resultJsonString(ResultCode.ERROR, errorMessage);
        }
    }
}

