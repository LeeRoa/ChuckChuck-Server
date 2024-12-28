package rise.cc.service;

import rise.cc.dto.Approval;

public interface ApprovalService {
    String createApproval(Approval approval);
    String updateApproval(Approval approval);
    String deleteApproval(Approval approval);
    String getApproval(Approval approval);
}
