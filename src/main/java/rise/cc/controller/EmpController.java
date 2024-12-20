package rise.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rise.cc.common.ResultCode;
import rise.cc.dto.Employees;
import rise.cc.service.EmpService;
import rise.cc.util.JsonUtils;
import rise.cc.util.MapUtils;

@Slf4j
@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmpController {

    private final EmpService empService;

    /**
     * 사원(계정)의 로그인을 판단하여 결과를 웹에 내려주는 컨트롤러
     * @param emp empEmail, empPw, isAutoLogin(Optional)
     * @return 검색된 회원의 데이터 Json 형식
     */
    @PostMapping("/login")
    public String login(Employees emp) {
        try {
            return empService.loginProc(MapUtils.ObjtoMap(emp));
        } catch (NullPointerException e) {
            log.error("로그인 요청 에러 : {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            return JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (JsonProcessingException e) {
            log.error("로그인 요청 에러 : {}", ResultCode.resultMsg(ResultCode.FORMAT_ERROR));
            return JsonUtils.resultJsonString(ResultCode.FORMAT_ERROR, ResultCode.FORMAT_ERROR_MSG);
        } catch (Exception e) {
            log.error("로그인 요청 에러 : {}", e.getMessage());
            e.printStackTrace();
            return JsonUtils.resultJsonString(ResultCode.ERROR, ResultCode.ERROR_MSG);
        }
    }

    /**
     * 사원(계정)을 조회하는 기능
     * @param emp empEmail, empPw, isAutoLogin(Optional)
     * @return 검색된 회원의 데이터 Json 형식
     */
    @GetMapping("/findEmp")
    public String findEmp(Employees emp) {
        try {
            return JsonUtils.objtoJson(empService.getEmp(MapUtils.ObjtoMap(emp)));
        } catch (JsonProcessingException e) {
            return JsonUtils.resultJsonString(ResultCode.FORMAT_ERROR, ResultCode.FORMAT_ERROR_MSG);
        } catch (Exception e) {
            return JsonUtils.resultJsonString(ResultCode.ERROR, ResultCode.ERROR_MSG);
        }
    }
}
