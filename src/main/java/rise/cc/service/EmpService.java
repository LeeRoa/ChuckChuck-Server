package rise.cc.service;

import rise.cc.dto.Employees;

public interface EmpService {

    String loginProc(Employees emp);

    String getEmp(Employees emp);

    String getEmpCount(Employees emp);

    String createEmp(Employees emp);

    String updateEmp(Employees emp);
}
