package rise.cc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class User {
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
        권한 고유ID	        role_no
        직속상사사원번호	    direct_boss_id
                            spare
     */
    private int emp_id;
    private String emp_email;
    private String emp_phonenum;
    private String emp_pw;
    private String emp_name;
    private Date emp_birth;
    private String emp_account_status;
    private String emp_position;
    private int pw_error_cnt;
    private Date emp_join_dt;
    private Date emp_retire_dt;
    private int department_id;
    private int rank_id;
    private String biz_no;
    private int role_no;
    private int direct_boss_id;
    private String spare;
}
