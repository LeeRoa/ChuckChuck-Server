package rise.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rise.cc.common.ResultCode;
import rise.cc.service.UserService;
import rise.cc.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Web 단에서 로그인 처리를 위한 요청을 받는 컨트롤러
     * @param userMap empEmail, empPw, isAutoLogin(Optional)
     * @return 검색된 회원의 데이터 Json 형식
     */
    @PostMapping("/login.do")
    public String login(Map<String, Object> userMap) {
        try {
            return JsonUtils.ObjtoJson(userService.getUser(userMap));
        } catch (JsonProcessingException e) {
            return ResultCode.resultJsonString(ResultCode.FORMAT_ERROR_MSG);
        } catch (Exception e) {
            return ResultCode.resultJsonString(ResultCode.ERROR);
        }
    }
}
