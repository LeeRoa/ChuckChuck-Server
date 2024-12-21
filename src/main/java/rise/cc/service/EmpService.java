package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.javassist.NotFoundException;
import rise.cc.dto.Employees;
import rise.cc.exception.EmpLoginException;

import java.util.Map;

public interface EmpService {

    String loginProc(Employees emp) throws JsonProcessingException;

    String getEmp(Employees emp) throws JsonProcessingException;

    String getEmpCount(Employees emp);
}
