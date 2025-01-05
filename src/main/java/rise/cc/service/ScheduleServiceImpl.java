package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        return processSingleOperation(result, "일정 그룹 생성 성공", "일정 그룹 생성 실패");
    }

    @Transactional
    @Override
    public String createSchedule(Schedule schedule) {
        validate(schedule, "scheduleName", "scheduleStartDt", "scheduleEndDt", "scheduleGroupId", "empId");
        int createSchedule = scheduleDao.createSchedule(schedule);
        if(createSchedule < 0) {
            return JsonUtils.resultJsonString(ResultCode.ERROR, ResultCode.ERROR_MSG);
        }

        if(schedule.getEmpId() != null) {
            int createScheduleEmp = scheduleDao.createScheduleEmployees(schedule);
            if(createScheduleEmp > 0) {
                log.info("해당 일정 작성 사원 추가 완료 {}", schedule.getEmpId());
                return JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
            } else {
                log.error("해당 일정 작성 사원 추가 실패");
                throw new DataAccessException("사원 추가 실패") {};
            }
        }
        return JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
    }

    @Transactional
    @Override
    public String inviteGroupMembers(List<ScheduleGroup> scheduleGroupsList) {
        for (ScheduleGroup scheduleGroup : scheduleGroupsList) {
            validate(scheduleGroup, "scheduleGroupId", "empId");
        }

        int result = scheduleDao.inviteGroupMembers(scheduleGroupsList);

        return processBatchOperation(result, scheduleGroupsList, "구성원 초대 성공", "구성원 초대 실패");
    }

    @Override
    public String selectSchedule(List<Schedule> scheduleList) throws JsonProcessingException {
        for(Schedule schedule : scheduleList) {
            validate(schedule, "scheduleGroupId", "empId", "scheduleDate");
        }

        List<Schedule> selectScheduleResult = scheduleDao.findSchedules(scheduleList);

        if(selectScheduleResult == null || selectScheduleResult.isEmpty()) {
            return JsonUtils.resultJsonString(ResultCode.SUCCESS, "일정이 존재하지 않습니다.");
        }

        return JsonUtils.addJsonValue(JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG), "selectScheduleResult", selectScheduleResult);

    }


    private void validate(Object entity, String... requiredFields) {
        for (String field : requiredFields) {
            try {
                Object value = Objects.requireNonNull(BeanUtils.getPropertyDescriptor(entity.getClass(), field)).getReadMethod().invoke(entity);
                if (value == null) {
                    throw new IllegalArgumentException(field + " 필드가 유효하지 않습니다.");
                }
            } catch (Exception e) {
                log.error("필수 값 오류: {}", field, e);
                throw new IllegalArgumentException(" 필수 값 오류: " + field);
            }
        }
    }

    private String processSingleOperation(int result, String successMessage, String errorMessage) {
        if (result > 0) {
            log.info(successMessage);
            return JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
        } else {
            log.error(errorMessage);
            return JsonUtils.resultJsonString(ResultCode.ERROR, errorMessage);
        }
    }

    private String processBatchOperation(int result, List<?> list, String successMessage, String errorMessage) {
        if (result == list.size()) {
            log.info(successMessage);
            return JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
        } else {
            log.error(errorMessage);
            return JsonUtils.resultJsonString(ResultCode.ERROR, errorMessage);
        }
    }


}

