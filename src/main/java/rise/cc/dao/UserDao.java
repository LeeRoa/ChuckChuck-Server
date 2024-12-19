package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import rise.cc.dto.User;

import java.util.Map;

@Mapper
public interface UserDao {
    User getUser(Map<String, Object> userMap);
}
