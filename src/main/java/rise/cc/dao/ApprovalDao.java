package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import rise.cc.dto.Approval;

import java.util.List;

@Mapper
public interface ApprovalDao {
    int createApproval(Approval approval);
    int updateApproval(Approval approval);
    int deleteApproval(Approval approval);
    List<Approval> getApproval(Approval approval);
    int createApprovalLine(Approval approval);
}
