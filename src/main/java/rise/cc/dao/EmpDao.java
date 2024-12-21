package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import rise.cc.dto.Employees;

@Mapper
public interface EmpDao {
    Employees loginProc(Employees emp);
    Employees getEmp(Employees emp);
    Integer getEmpCount(Employees emp);
    Integer createEmp(Employees emp);
}
