package rise.cc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.common.ApprovalResultCode;
import rise.cc.common.EmpResultCode;
import rise.cc.common.ResultCode;
import rise.cc.dao.ApprovalDao;
import rise.cc.dto.Approval;
import rise.cc.service.ApprovalService;
import rise.cc.util.JsonUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;
    private String resultMsg = JsonUtils.resultJsonString(EmpResultCode.ERROR, EmpResultCode.ERROR_MSG);

    @Transactional
    @Override
    public String createApproval(Approval approval) {
        try {
            if (approval.getEmpId() == null) {
                throw new NullPointerException();
            }

            setApprovalTotalLine(approval);
            if (approval.getRelateNo() != null) {
                approval.setRelateNoArr(approval.getRelateNo().trim().split(","));
            }

            if (approvalDao.createApproval(approval) > 0) {
                log.info("전자결재 문서 등록 성공.");
                resultMsg = JsonUtils.resultJsonString(ApprovalResultCode.SUCCESS, ApprovalResultCode.SUCCESS_MSG);
            }
        } catch (NullPointerException e) {
            log.error("전자결재 문서 등록 에러: {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            e.printStackTrace();
            resultMsg = JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (Exception e) {
            log.error("전자결재 문서 등록 에러: {}", e.getMessage());
            e.printStackTrace();
        }

        return resultMsg;
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

    /**
     * 파라미터로 받은 Approval 객체에 결재라인, 통보, 참조자 값이 들어있다면 해당 값을 파싱한다.
     * 값이 들어있지 않다면 DB에서 결재라인 설정이 존재하는지 조회한 후 설정한다.
     * @param approval (설정할 Approval 객체)
     */
    public void setApprovalTotalLine(Approval approval) {
        if (approval.getApproLine() == null) {
            //ToDo 기존 결재라인 설정 정보 DB 검색
        } else {
            approval.setApproLineArr(approval.getApproLine().trim().split(","));
        }

        if (approval.getRefEmp() == null) {
            //ToDo 기존 참조자 설정 정보 DB 검색
        } else {
            approval.setRefEmpArr(approval.getRefEmp().trim().split(","));
        }

        if (approval.getNotificationEmp() == null) {
            //ToDo 기존 통보자 설정 정보 DB 검색
        } else {
            approval.setNotificationEmpArr(approval.getNotificationEmp().trim().split(","));
        }
    }
}
