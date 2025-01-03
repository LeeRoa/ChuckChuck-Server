package rise.cc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.common.ResultCode;
import rise.cc.dao.ScheduleDao;
import rise.cc.dto.Schedule;
import rise.cc.dto.ScheduleGroup;
import rise.cc.util.JsonUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;

    @Override
    public String createScheduleGroup(ScheduleGroup scheduleGroup) {
        validate(scheduleGroup, "scheduleGroupName", "empId");
        int result = scheduleDao.createScheduleGroup(scheduleGroup);
        return processDatabaseOperation(result, "일정 그룹 생성 성공", "일정 그룹 생성 실패");
    }

    @Override
    public String createSchedule(Schedule schedule) {
        validate(schedule, "scheduleName", "scheduleStartDt", "scheduleEndDt", "scheduleGroupId");
        int result = scheduleDao.createSchedule(schedule);
        return processDatabaseOperation(result, "일정 생성 성공", "일정 생성 실패");
    }

    @Transactional
    @Override
    public String inviteGroupMembers(List<ScheduleGroup> scheduleGroupsList) {
        for (ScheduleGroup scheduleGroup : scheduleGroupsList) {
            validate(scheduleGroup, "scheduleGroupId", "empId");
            int result = scheduleDao.inviteGroupMembers(scheduleGroup);
            if (result <= 0) {
                log.error("구성원 초대 실패: {}", scheduleGroup);
                throw new IllegalArgumentException("구성원 초대 실패: " + scheduleGroup);
            }
        }
        log.info("모든 구성원 초대 성공");
        return JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
    }


    private void validate(Object entity, String... requiredFields) {
        for (String field : requiredFields) {
            try {
                Object value = Objects.requireNonNull(BeanUtils.getPropertyDescriptor(entity.getClass(), field)).getReadMethod().invoke(entity);
                if (value == null) {
                    throw new IllegalArgumentException(field + " 필드가 유효하지 않습니다.");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(" 필수 값 오류: " + field);
            }
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

