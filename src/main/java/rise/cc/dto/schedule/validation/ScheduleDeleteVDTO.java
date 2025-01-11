package rise.cc.dto.schedule.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rise.cc.dto.schedule.ScheduleDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ScheduleDeleteVDTO extends ScheduleDTO {

    @NotNull(message = "scheduleGroupId은 필수 입력값 입니다")
    private Integer ScheduleGroupId;

    @NotNull(message = "scheduleId 필수 입력값 입니다")
    private Integer ScheduleId;
}
