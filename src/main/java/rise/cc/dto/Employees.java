package rise.cc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Employees {
    /*
        사원번호	            emp_id
        이메일(사용자 아이디)	emp_email
        전화번호	            emp_phonenum
        사용자 비밀번호	    emp_pw
        이름	                emp_name
        생년월일	            emp_birth
        계정 상태	        emp_account_status
        직책	                emp_position
        비밀번호 오류 휫수  	pw_error_cnt
        입사일	            emp_join_dt
        퇴사일	            emp_retire_dt
        부서고유ID	        department_id
        직급고유ID	        rank_id
        사업자 번호	        biz_no
        권한 고유ID	        role_id
        직속상사사원번호	    direct_boss_id
                            spare
     */
    private String empId;
    private String empEmail;
    private String empPhonenum;
    private String empPw;
    private String empName;
    private Date empBirth;
    private String empAccountStatus;
    private String empPosition;
    private String pwErrorCnt;
    private Date empJoinDt;
    private Date empRetireDt;
    private String departmentId;
    private String rankId;
    private String bizNo;
    private String roleId;
    private String directBossId;
    private String spare;

    /* cc_dep */
    private String departmentName;

    /* cc_code */
    private String rankName;
    private String role;
    private String roleLevel; // col_seq 컬럼과 같은 데이터

    public void setRole(String role) {
        this.role = role;
        this.roleLevel = roleLevel;
    }
}
