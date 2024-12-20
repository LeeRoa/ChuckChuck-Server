package rise.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.javassist.NotFoundException;
import rise.cc.dto.Employees;
import rise.cc.exception.EmpLoginException;

import java.util.Map;

public interface EmpService {

    String loginProc(Map<String, Object> empMap) throws JsonProcessingException;

    Employees getEmp(Map<String, Object> empMap);

    Integer getEmpCount(Map<String, Object> empMap);
}
