package rise.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rise.cc.common.ResultCode;
import rise.cc.dto.Employees;
import rise.cc.service.EmpService;
import rise.cc.util.JsonUtils;

@Slf4j
@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmpController {

    private final EmpService empService;

    /**
     * 사원(계정)의 로그인을 판단하여 결과를 웹에 내려주는 컨트롤러
     * @param emp empEmail
     * @return 검색된 회원의 데이터 Json 형식
     */
    @PostMapping("/login")
    public String login(@RequestBody Employees emp) {
        try {
            return empService.loginProc(emp);
        } catch (NullPointerException e) {
            log.error("로그인 프로세스 요청 에러 : {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            return JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (JsonProcessingException e) {
            log.error("로그인 프로세스 요청 에러 : {}", ResultCode.resultMsg(ResultCode.FORMAT_ERROR));
            return JsonUtils.resultJsonString(ResultCode.FORMAT_ERROR, ResultCode.FORMAT_ERROR_MSG);
        } catch (Exception e) {
            log.error("로그인 프로세스 요청 에러 : {}", e.getMessage());
            e.printStackTrace();
            return JsonUtils.resultJsonString(ResultCode.ERROR, ResultCode.ERROR_MSG);
        }
    }

    /**
     * 사원(계정)을 조회하는 기능
     * @param emp empId, empEmail 등
     * @return 검색된 회원의 데이터 Json 형식
     */
    @GetMapping("/findEmp")
    public String findEmp(@RequestBody Employees emp) {
        try {
            return empService.getEmp(emp);
        } catch (NullPointerException e) {
            log.error("사원 찾기 요청 에러 : {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            return JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (JsonProcessingException e) {
            log.error("사원 찾기 에러 : {}", ResultCode.resultMsg(ResultCode.FORMAT_ERROR));
            return JsonUtils.resultJsonString(ResultCode.FORMAT_ERROR, ResultCode.FORMAT_ERROR_MSG);
        } catch (Exception e) {
            log.error("사원 찾기 에러 : {}", e.getMessage());
            e.printStackTrace();
            return JsonUtils.resultJsonString(ResultCode.ERROR, ResultCode.ERROR_MSG);
        }
    }

    @PostMapping("")
    public String createEmp(@RequestBody Employees emp) {
        return null;
    }
}
