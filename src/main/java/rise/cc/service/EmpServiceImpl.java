package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.common.EmpResultCode;
import rise.cc.dao.EmpDao;
import rise.cc.dto.Employees;
import rise.cc.util.JsonUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmpDao empDao;

    @Transactional
    @Override
    public String loginProc(Employees emp) throws JsonProcessingException {
        Employees findEmp = empDao.loginProc(emp);
        if (findEmp == null) {
            log.error("로그인 프로세스 요청 에러 : {}", EmpResultCode.resultMsg(EmpResultCode.EMP_NOT_FOUND));
            return JsonUtils.resultJsonString(EmpResultCode.EMP_NOT_FOUND, EmpResultCode.EMP_NOT_FOUND_MSG);
        }

        log.info("로그인 프로세스 성공");
        return JsonUtils.addJsonValue(JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG), "empInfo", findEmp);
    }

    @Override
    public String getEmp(Employees emp) throws JsonProcessingException {
        Employees findEmp = empDao.getEmp(emp);
        if (findEmp == null) {
            log.error("사원 찾기 요청 에러 : {}", EmpResultCode.resultMsg(EmpResultCode.EMP_NOT_FOUND));
            return JsonUtils.resultJsonString(EmpResultCode.EMP_NOT_FOUND, EmpResultCode.EMP_NOT_FOUND_MSG);
        }

        log.info("사원 찾기 성공");
        return JsonUtils.addJsonValue(JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG), "empInfo", findEmp);
    }

    @Override
    public String getEmpCount(Employees emp) {
        return null;
    }

    @Override
    public String createEmp(Employees emp) {
        try {
            if (empDao.createEmp(emp) > 0) {
                log.info("계정 생성 성공. 생성된 계정 : {}", emp.getEmpId());
                return JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error("계정 생성 DB 에러 : {}", e.getMessage());
            return JsonUtils.resultJsonString(EmpResultCode.DB_ERROR, EmpResultCode.DB_ERROR_MSG);
        } catch (Exception e) {
            log.error("계정 생성 에러 : {}", e.getMessage());
            e.printStackTrace();
        }
        return JsonUtils.resultJsonString(EmpResultCode.ERROR, EmpResultCode.ERROR_MSG);
    }
}
