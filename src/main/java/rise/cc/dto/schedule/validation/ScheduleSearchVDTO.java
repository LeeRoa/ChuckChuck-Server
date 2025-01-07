package rise.cc.dto.schedule.validation;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rise.cc.dto.schedule.ScheduleDTO;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ScheduleSearchVDTO extends ScheduleDTO {

    @NotNull(message = "scheduleGroupId는 필수 입력값 입니다")
    private Integer scheduleGroupId;

    @NotNull(message = "empId는 필수 입력값 입니다")
    private Integer empId;

    @NotNull(message = "scheduleDate는 필수 입력값 입니다")
    private Timestamp scheduleDate;

}
