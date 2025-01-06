package rise.cc.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CreateScheduleDTO extends ScheduleBaseDTO{

    @NotBlank(message = "scheduleName은 필수 입력값 입니다")
    private String scheduleName;

    @NotNull(message = "scheduleGroupId는 필수 입력값 입니다")
    private Integer scheduleGroupId;
    
    @NotNull(message = "scheduleStartDt는 필수 입력값 입니다")
    private Timestamp scheduleStartDt;

    @NotNull(message = "scheduleEndDt는 필수 입력값 입니다")
    private Timestamp scheduleEndDt;

    @NotNull(message = "empId는 필수 입력값 입니다")
    private Integer empId;
}
