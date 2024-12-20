package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import rise.cc.dto.Employees;

import java.util.Map;

@Mapper
public interface EmpDao {
    Employees loginProc(Map<String, Object> empMap);
    Employees getEmp(Map<String, Object> empMap);

    Integer getEmpCount(Map<String, Object> empMap);
}
