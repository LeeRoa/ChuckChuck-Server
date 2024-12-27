package rise.cc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rise.cc.dto.Employees;
import rise.cc.service.EmpService;

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
        return empService.loginProc(emp);
    }

    /**
     * 사원(계정)을 조회하는 기능
     * @param emp empId, empEmail 등
     * @return 검색된 회원의 데이터 Json 형식
     */
    @GetMapping("")
    public String findEmp(Employees emp) {
        return empService.getEmp(emp);
    }

    /**
     * 계정을 생성하는 컨트롤러
     * @param emp empId, empPhonenum, empBirth, empPw,
     * @return 처리 결과 값 Json String
     */
    @PostMapping("")
    public String createEmp(@RequestBody Employees emp) {
        return empService.createEmp(emp);
    }
}
