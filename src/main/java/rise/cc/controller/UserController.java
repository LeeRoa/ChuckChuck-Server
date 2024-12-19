package rise.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rise.cc.common.ResultCode;
import rise.cc.dto.User;
import rise.cc.service.UserService;
import rise.cc.util.JsonUtils;
import rise.cc.util.MapUtils;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사원(계정)을 조회하는 기능
     * @param user empEmail, empPw, isAutoLogin(Optional)
     * @return 검색된 회원의 데이터 Json 형식
     */
    @GetMapping("/findUser")
    public String findUser(User user) {
        try {
            return JsonUtils.ObjtoJson(userService.getUser(MapUtils.ObjtoMap(user)));
        } catch (JsonProcessingException e) {
            return ResultCode.resultJsonString(ResultCode.FORMAT_ERROR_MSG);
        } catch (Exception e) {
            return ResultCode.resultJsonString(ResultCode.ERROR);
        }
    }
}
