package rise.cc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ScheduleGroup {
    /*
        일정그룹 고유ID	schedule_group_id	int
        그룹 명	schedule_group_name	varchar
        그룹 설명	schedule_group_description	varchar
        그룹 생성 일시	schedule_group_create_dt	timestamp
        그룹 수정 일시	schedule_group_update_dt	timestamp
        그룹장 사원번호	emp_id	int
        spare
     */

    private int scheduleGroupId;
    private String scheduleGroupName;
    private String scheduleGroupDescription;
    private Date scheduleGroupCreateDt;
    private Date scheduleGroupUpdateDt;
    private int empId;
    private String spare;
}
