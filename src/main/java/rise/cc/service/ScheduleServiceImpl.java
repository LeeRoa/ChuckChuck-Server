package rise.cc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import rise.cc.common.EmpResultCode;
import rise.cc.common.ResultCode;
import rise.cc.dao.ScheduleDao;
import rise.cc.dto.ScheduleGroup;
import rise.cc.util.JsonUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;

    @Override
    public String createScheduleGroup(ScheduleGroup scheduleGroup) {

        try {
            if(scheduleDao.createScheduleGroup(scheduleGroup) > 0) {
                log.info("일정 그룹 생성 성공. 생성자 : {}", scheduleGroup.getEmpId());
                return JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
            }
        } catch (NullPointerException e) {
            log.error("그룹 생성 실패: {}", ResultCode.NO_REQUIRED_PARAM_MSG);
            return JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);

        } catch (DataAccessException e) {
            log.error("그룹 생성 DB 에러 로그 확인 필요. {}", e.getMessage());
            return JsonUtils.resultJsonString(ResultCode.DB_ERROR, ResultCode.DB_ERROR_MSG);

        } catch (Exception e) {
            log.error("일정 그룹 생성 에러: {}", e.getMessage());
            e.printStackTrace();
        }

        return JsonUtils.resultJsonString(ResultCode.ERROR, ResultCode.ERROR_MSG);

    }
}
