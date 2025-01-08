package rise.cc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rise.cc.common.SearchCriteria;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApprovalLiner extends SearchCriteria {
    /*
            결재 상태 번호	status_no
            라인 타입	    line_type
            문서번호	        doc_no
            결재자 사원번호	emp_id
            결재 상태 코드	status_code
            결재 순서	    appro_seq
            결재 일시	    appro_dt
            의견 내용	    appro_comment
            의견 작성일	    comment_dt
            spare
     */
    private String statusNo;
    private String lineType;
    private String docNo;
    private String empId;
    private String statusCode;
    private String approSeq;
    private Date approDt;
    private String approComment;
    private Date commentDt;
    private String spare;

    public static final String APPROVAL_TYPE = "APPROVAL";
    public static final String REF_EMP_TYPE = "REF_EMP";
    public static final String NOTIFICATION_EMP_TYPE = "NOTIFICATION_EMP";
    public static final String RELATE_TYPE = "RELATE_NO";
}
