package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import rise.cc.dto.Employees;

@Mapper
public interface EmpDao {
    Employees loginProc(Employees emp) throws DataAccessException;
    Employees getEmp(Employees emp) throws DataAccessException;
    Integer getEmpCount(Employees emp) throws DataAccessException;
    Integer createEmp(Employees emp) throws DataAccessException;
    Integer updateEmp(Employees emp) throws DataAccessException;
    Employees getRole(Employees emp) throws DataAccessException;
}
