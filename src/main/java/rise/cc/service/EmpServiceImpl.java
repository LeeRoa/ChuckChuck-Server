package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.common.EmpResultCode;
import rise.cc.common.ResultCode;
import rise.cc.dao.EmpDao;
import rise.cc.dto.Employees;
import rise.cc.exception.EmpException;
import rise.cc.util.JsonUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmpDao empDao;
    String resultMsg = JsonUtils.resultJsonString(EmpResultCode.ERROR, EmpResultCode.ERROR_MSG);

    @Transactional
    @Override
    public String loginProc(Employees emp) {
        try {
            Employees findEmp = empDao.loginProc(emp);
            if (findEmp == null) {
                log.error("로그인 프로세스 요청 에러: {}", EmpResultCode.resultMsg(EmpResultCode.EMP_NOT_FOUND));
                resultMsg = JsonUtils.resultJsonString(EmpResultCode.EMP_NOT_FOUND, EmpResultCode.EMP_NOT_FOUND_MSG);
            } else {
                log.info("로그인 프로세스 성공");
                resultMsg = JsonUtils.addJsonValue(JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG), "empInfo", findEmp);
            }
        } catch (NullPointerException e) {
            log.error("로그인 프로세스 요청 에러: {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            resultMsg = JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (DataAccessException e) {
            log.error("로그인 DB 에러 로그 확인 필요. {}", e.getMessage());
            resultMsg = JsonUtils.resultJsonString(EmpResultCode.DB_ERROR, EmpResultCode.DB_ERROR_MSG);
        } catch (JsonProcessingException e) {
            log.error("로그인 프로세스 요청 에러: {}", ResultCode.resultMsg(ResultCode.FORMAT_ERROR));
            resultMsg = JsonUtils.resultJsonString(ResultCode.FORMAT_ERROR, ResultCode.FORMAT_ERROR_MSG);
        } catch (Exception e) {
            log.error("로그인 프로세스 요청 에러: {}", e.getMessage());
            e.printStackTrace();
        }
        return resultMsg;
    }

    @Override
    public String getEmp(Employees emp) {
        try {
            Employees findEmp = empDao.getEmp(emp);
            if (findEmp == null) {
                resultMsg = JsonUtils.resultJsonString(EmpResultCode.EMP_NOT_FOUND, EmpResultCode.EMP_NOT_FOUND_MSG);
                throw new EmpException(EmpResultCode.resultMsg(EmpResultCode.EMP_NOT_FOUND));
            }
            log.info("사원 조회 성공.");
            resultMsg = JsonUtils.addJsonValue(JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG), "empInfo", findEmp);
        } catch (NullPointerException e) {
            log.error("사원 조회 요청 에러: {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            resultMsg = JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (DataAccessException e) {
            log.error("사원 조회 DB 에러 로그 확인 필요. {}", e.getMessage());
            resultMsg = JsonUtils.resultJsonString(EmpResultCode.DB_ERROR, EmpResultCode.DB_ERROR_MSG);
        } catch (JsonProcessingException e) {
            log.error("사원 조회 에러: {}", ResultCode.resultMsg(ResultCode.FORMAT_ERROR));
            resultMsg = JsonUtils.resultJsonString(ResultCode.FORMAT_ERROR, ResultCode.FORMAT_ERROR_MSG);
        } catch (EmpException e) {
            log.error("사원 조회 에러: {}", e.getMessage());
        } catch (Exception e) {
            log.error("사원 조회 에러: {}", e.getMessage());
            e.printStackTrace();
        }
        return resultMsg;
    }

    @Override
    public String getEmpCount(Employees emp) {
        return null;
    }

    @Override
    public String createEmp(Employees emp) {
        try {
            if (empDao.createEmp(emp) > 0) {
                log.info("계정 생성 성공. 생성된 계정: {}", emp.getEmpEmail());
                resultMsg = JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG);
            }
        } catch (NullPointerException e) {
            log.error("계정 생성 에러: {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            resultMsg = JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (DataAccessException e) {
            log.error("계정 생성 DB 에러 로그 확인 필요. {}", e.getMessage());
            resultMsg = JsonUtils.resultJsonString(EmpResultCode.DB_ERROR, EmpResultCode.DB_ERROR_MSG);
        } catch (Exception e) {
            log.error("계정 생성 에러: {}", e.getMessage());
            e.printStackTrace();
        }
        return resultMsg;
    }

    @Transactional
    @Override
    public String updateEmp(Employees emp) {
        try {
            if (emp.getEmpId() == null) {
                throw new NullPointerException();
            } else if (emp.getRole() == null) {
                setRole(emp);
            }
            if (empDao.updateEmp(emp) > 0) {
                log.info("계정 정보 변경 성공. 변경된 계정: {}", emp.getEmpId());
                resultMsg = JsonUtils.resultJsonString(EmpResultCode.SUCCESS, EmpResultCode.SUCCESS_MSG);
            }
        } catch (NullPointerException e) {
            log.error("계정 정보 변경 ID: {}, 에러: {}", emp.getEmpId(), ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            e.printStackTrace();
            resultMsg = JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (DataAccessException e) {
            log.error("계정 정보 변경 ID: {}, DB 에러 로그 확인 필요. {}",emp.getEmpId(), e.getMessage());
            e.printStackTrace();
            resultMsg = JsonUtils.resultJsonString(EmpResultCode.DB_ERROR, EmpResultCode.DB_ERROR_MSG);
        } catch (Exception e) {
            log.error("계정 정보 변경 ID: {}, 에러 : {}", emp.getEmpId(), e.getMessage());
            e.printStackTrace();
        }

        return resultMsg;
    }

    /**
     * 파라미터로 받은 사용자의 권한을 세팅한다.
     * 권한의 레벨이 1 이하라면 기본 권한을 세팅한다.
     * @param emp (사용자 객체)
     */
    public void setRole(Employees emp) {
        try {
            Employees getRole = empDao.getRole(emp);

            emp.setRole(getRole.getRole());
            emp.setRoleLevel(getRole.getRoleLevel());
        } catch (NullPointerException e) {
            emp.setRole("ROLE_EMP");
            emp.setRoleLevel("1");
        }
    }
}
