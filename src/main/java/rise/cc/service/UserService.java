package rise.cc.service;

import rise.cc.dto.User;

import java.util.Map;

public interface UserService {
    User getUser(Map<String, Object> userMap);
}
