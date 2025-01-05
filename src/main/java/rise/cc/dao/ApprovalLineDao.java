package rise.cc.dao;

import org.apache.ibatis.annotations.Mapper;
import rise.cc.dto.ApprovalLiner;

import java.util.List;

@Mapper
public interface ApprovalLineDao {
    int createApprovalLine(List<ApprovalLiner> approvalLiners);
}
