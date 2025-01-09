package rise.cc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.common.ResultCode;
import rise.cc.dao.ScheduleDao;
import rise.cc.dto.schedule.ScheduleDTO;
import rise.cc.service.ScheduleService;
import rise.cc.util.JsonUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;

    @Override
    public String createScheduleGroup(ScheduleDTO schedule) {
        int result = scheduleDao.scheduleGroupCreate(schedule);
        return processSingleOperation(result, "일정 그룹 생성 성공", "일정 그룹 생성 실패");
    }

    @Transactional
    @Override
    public String createSchedule(ScheduleDTO schedule) {
        int createSchedule = scheduleDao.scheduleCreate(schedule);
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
    public String scheduleGroupMemberAdd(List<? extends ScheduleDTO> scheduleList) {

        int result = scheduleDao.scheduleGroupMemberAdd(scheduleList);

        return processBatchOperation(result, scheduleList, "구성원 초대 성공", "구성원 초대 실패");
    }

    @Override
    public String selectSchedule(ScheduleDTO schedule) throws JsonProcessingException {

        List<ScheduleDTO> selectScheduleResult = scheduleDao.scheduleSearch(schedule);

        if(selectScheduleResult == null || selectScheduleResult.isEmpty()) {
            return JsonUtils.resultJsonString(ResultCode.SUCCESS, "일정이 존재하지 않습니다.");
        }

        return JsonUtils.addJsonValue(JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG), "scheduleSearchResult", selectScheduleResult);
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

