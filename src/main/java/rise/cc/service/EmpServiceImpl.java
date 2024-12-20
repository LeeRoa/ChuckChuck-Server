package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.common.EmpResultCode;
import rise.cc.dao.EmpDao;
import rise.cc.dto.Employees;
import rise.cc.util.JsonUtils;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmpDao empDao;

    @Transactional
    @Override
    public String loginProc(Map<String, Object> empMap) throws JsonProcessingException {
        if (empDao.getEmpCount(empMap) < 1) {
            System.out.println("1");
            log.error("로그인 요청 에러 : {}", EmpResultCode.resultMsg(EmpResultCode.ID_NOT_FOUND));
            return JsonUtils.resultJsonString(EmpResultCode.ID_NOT_FOUND, EmpResultCode.ID_NOT_FOUND_MSG);
        }

        Employees employees = empDao.loginProc(empMap);

        if (employees == null) {
            System.out.println("2");
            log.error("로그인 요청 에러 : {}", EmpResultCode.resultMsg(EmpResultCode.PASSWORD_INCORRECT));
            return JsonUtils.resultJsonString(EmpResultCode.PASSWORD_INCORRECT, EmpResultCode.PASSWORD_INCORRECT_MSG);
        }

        log.info("로그인 성공");
        return JsonUtils.addJsonValue(JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG), "empInfo", employees);
    }

    @Override
    public Employees getEmp(Map<String, Object> empMap) {
        return empDao.getEmp(empMap);
    }

    @Override
    public Integer getEmpCount(Map<String, Object> empMap) {
        return empDao.getEmpCount(empMap);
    }
}
