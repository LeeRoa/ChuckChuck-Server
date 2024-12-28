package rise.cc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rise.cc.dao.ApprovalDao;
import rise.cc.dto.Approval;
import rise.cc.service.ApprovalService;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;

    @Override
    public String createApproval(Approval approval) {
        return null;
    }

    @Override
    public String updateApproval(Approval approval) {
        return null;
    }

    @Override
    public String deleteApproval(Approval approval) {
        return null;
    }

    @Override
    public String getApproval(Approval approval) {
        return null;
    }
}
