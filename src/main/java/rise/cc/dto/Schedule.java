package rise.cc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Schedule {
    /*
    일정 고유 ID	schedule_id	int
    일정 제목	schedule_name	int
    일정 내용	schedule_content	varchar
    일정 시작 일시	schedule_start_dt	timestamp
    일정 종료 일시	schedule_end_dt	timestamp
    일정 장소	schedule_place	varchar
    일정 등록 일시	schedule_register_dt	timestamp
    일정 수정 일시	schedule_update_dt	timestamp
    종일 일정 여부	allday_whether	char
    그룹 고유ID	schedule_group_id	int
    spare
     */
    private Integer scheduleId;
    private String scheduleName;
    private String scheduleContent;
    private String schedulePlace;
    private Character alldayWhether;
    private Integer scheduleGroupId;
    private Integer empId;
    private String spare;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduleDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduleStartDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduleEndDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduleRegisterDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduleUpdateDt;



}
