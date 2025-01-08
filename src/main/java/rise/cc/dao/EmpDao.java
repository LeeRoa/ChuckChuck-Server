package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import rise.cc.dto.Employees;

import java.util.List;

@Mapper
public interface EmpDao {
    Employees loginProc(Employees emp) throws DataAccessException;
    List<Employees> getEmp(Employees emp) throws DataAccessException;
    int getEmpCount(Employees emp) throws DataAccessException;
    int createEmp(Employees emp) throws DataAccessException;
    int updateEmp(Employees emp) throws DataAccessException;
    Employees getRole(Employees emp) throws DataAccessException;
}
