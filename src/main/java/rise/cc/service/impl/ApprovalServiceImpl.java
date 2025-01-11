package rise.cc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rise.cc.common.EmpResultCode;
import rise.cc.common.ResultCode;
import rise.cc.dao.ApprovalDao;
import rise.cc.dao.ApprovalLineDao;
import rise.cc.dto.Approval;
import rise.cc.dto.ApprovalLiner;
import rise.cc.service.ApprovalService;
import rise.cc.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;
    private final ApprovalLineDao approvalLineDao;
    private String resultMsg = JsonUtils.resultJsonString(EmpResultCode.ERROR, EmpResultCode.ERROR_MSG);

    @Transactional
    @Override
    public String createApproval(Approval approval) {
        try {
            if (approval.getEmpId() == null) {
                throw new NullPointerException();
            }

            if (approvalDao.createApproval(approval) > 0) {
                log.info("전자결재 문서 등록 성공.");
                // ToDo 전자결재 문서 등록 후 전자결재 라인 테이블에 로우 추가
                setApproval(approval);

                Optional.ofNullable(approval.getApproLineList()).ifPresent(approvalLineDao::createApprovalLine);
                log.info("전자결재 번호 : {}, 전자결재 라인 설정 완료.", approval.getDocNo());

                Optional.ofNullable(approval.getRefEmpList()).ifPresent(approvalLineDao::createApprovalLine);
                log.info("전자결재 번호 : {}, 참조자 설정 완료.", approval.getDocNo());

                Optional.ofNullable(approval.getNotificationEmpList()).ifPresent(approvalLineDao::createApprovalLine);
                log.info("전자결재 번호 : {}, 통보자 설정 완료.", approval.getDocNo());

                resultMsg = JsonUtils.resultJsonString(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG);
            }
        } catch (NullPointerException e) {
            log.error("전자결재 문서 등록 에러: {}", ResultCode.resultMsg(ResultCode.NO_REQUIRED_PARAM));
            e.printStackTrace();
            resultMsg = JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, ResultCode.NO_REQUIRED_PARAM_MSG);
        } catch (DataAccessException e) {
            log.error("전자결재 문서 등록 DB 에러 로그 확인 필요. {}", e.getMessage());
            resultMsg = JsonUtils.resultJsonString(EmpResultCode.DB_ERROR, EmpResultCode.DB_ERROR_MSG);
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
    public void setApproval(Approval approval) {
        if (approval.getApproLine() == null) {
            //ToDo 기존 결재라인 설정 정보 DB 검색
        } else {
            approval.setApproLineList(setApproLineListOrRelateList(approval.getApproLine().trim().split(","), approval));
        }

        if (approval.getRefEmp() == null) {
            //ToDo 기존 참조자 설정 정보 DB 검색
        } else {
            approval.setRefEmpList(setApproLineListOrRelateList(approval.getApproLine().trim().split(","), approval));
        }

        if (approval.getNotificationEmp() == null) {
            //ToDo 기존 통보자 설정 정보 DB 검색
        } else {
            approval.setNotificationEmpList(setApproLineListOrRelateList(approval.getApproLine().trim().split(","), approval));
        }

        if (approval.getRelateNo() != null) {
            List<Approval> approvalList = new ArrayList<>();
            String[] approvals = approval.getRelateNo().trim().split(",");

            for (String empId : approvals) {
                Approval tempApproval = new Approval();
                approval.setEmpId(empId);
                approvalList.add(tempApproval);
            }

            approval.setRelateNoList(approvalList);
        }
    }

    private List<ApprovalLiner> setApproLineListOrRelateList(String[] lines, Approval approval) {
        List<ApprovalLiner> approvalLiners = new ArrayList<>();

        for (String liner : lines) {
            ApprovalLiner approvalLiner = new ApprovalLiner();
            approvalLiner.setEmpId(liner);
            approvalLiner.setDocNo(approval.getDocNo());
            approvalLiners.add(approvalLiner);
        }
        return approvalLiners;
    }
}
