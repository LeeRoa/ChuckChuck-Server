package rise.cc.dto.schedule.validation;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rise.cc.dto.schedule.ScheduleDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ScheduleGroupMemberAddVDTO extends ScheduleDTO {

    @NotNull(message = "scheduleGroupId는 필수 입력값 입니다")
    private Integer scheduleGroupId;

    @NotNull(message = "empId는 필수 입력값 입니다")
    private Integer empId;
}
