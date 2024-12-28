package rise.cc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Approval {
    /*
        문서번호	            doc_no
        문서 타입 번호	    doc_type
        상신인 사원번호	    emp_id
        보존연한	            retention_period
        제목	                doc_title
        내용	                doc_content
        문서 생성일시	        create_dt
        결재 마감일시	        deadline_dt
        임시저장 여부	        draft_YN
        삭제여부	            delete_YN
        결재 라인 수	        total_line_count
        현재 결재 진행 순서	status_line_count
        spare
     */
    private String docNo;
    private String docType;
    private String empId;
    private Date retentionPeriod;
    private String docTitle;
    private String docContent;
    private Date createDt;
    private Date deadlineDt;
    private String draftYn;
    private String deleteYn;
    private String totalLineCount;
    private String statusLineCount;
    private String spare;

    /* request 용도 */
    private String approLine;       // 결재 라인
    private String refEmp;          // 참조자
    private String notificationEmp; // 통보자
    private String relateNo;        // 관련문서 번호

    /* dao 용도 */
    private String[] approLineArr;
    private String[] refEmpArr;
    private String[] notificationEmpArr;
    private String[] relateNoArr;
}
