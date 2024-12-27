package rise.cc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rise.cc.common.Role;
import rise.cc.dto.Employees;
import rise.cc.service.EmpService;

/**
 * 관리자(인사팀)의 권한이 존재하는 계정만 사용할 수 있는 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EmpService empService;

    /**
     * 인사관리자가 사원의 정보를 변경하는 컨트롤러
     *
     * @param emp (변경할 사원 정보)
     * @return 결과 코드, 메시지
     */
    @PatchMapping("/emp")
    public String updateEmp(@RequestBody Employees emp) {
        emp.setRole(Role.ROLE_ADMIN);
        return empService.updateEmp(emp);
    }
}
