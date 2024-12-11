package rise.cc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rise.cc.dao.TestDao;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestDao testDao;

    @Override
    public void test() {
        testDao.test();
    }
}
