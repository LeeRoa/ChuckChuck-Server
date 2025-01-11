package rise.cc.dto.schedule.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rise.cc.dto.schedule.ScheduleDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ScheduleGroupCreateVDTO extends ScheduleDTO {

    @NotBlank(message = "scheduleGroupName은 필수 입력값 입니다")
    private String scheduleGroupName;

    @NotNull(message = "empId는 필수 입력값 입니다")
    private Integer empId;
}
