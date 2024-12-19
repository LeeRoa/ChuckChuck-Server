package rise.cc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rise.cc.dao.UserDao;
import rise.cc.dto.User;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    @Override
    public User getUser(Map<String, Object> userMap) {
        return userDao.getUser(userMap);
    }
}
