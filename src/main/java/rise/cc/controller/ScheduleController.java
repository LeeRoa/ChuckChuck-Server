package rise.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rise.cc.dto.schedule.validation.*;
import rise.cc.service.ScheduleService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 그룹 생성 요청
     * @param schedule 필수 값:
     *                 - Integer scheduleGroupName : 생성할 그룹 이름
     *                 - Integer empId : 그룹의 생성자(대표자)
     * @return 성공 실패여부(실패 이유설명)
     */
    @PostMapping("/group-create")
    public String scheduleGroupCreate(@Valid @RequestBody ScheduleGroupCreateVDTO schedule) {
        return scheduleService.createScheduleGroup(schedule);
    }

    /**
     * 일정 생성 요청 데이터
     @param schedule 필수 값:
      *                 - String scheduleName: 일정 이름
      *                 - Integer scheduleGroupId: 일정 그룹 ID
      *                 - Timestamp scheduleStartDt: 일정 시작 날짜/시간
      *                 - Timestamp scheduleEndDt: 일정 종료 날짜/시간
      *                 - Integer empId: 담당자 ID
      * @return 성공 또는 실패 여부 (실패 시 이유 설명 포함)
     */
    @PostMapping("/create")
    public String scheduleCreate(@Valid @RequestBody ScheduleCreateVDTO schedule) {
        return scheduleService.createSchedule(schedule);
    }

    /**
     * 생성된 그룹에 구성원 추가
     * @param scheduleList 필수 값:
     *                 - Integer scheduleGroupId : 구성원을 포함시킬 그룹의 고유번호
     *                 - Integer empId : 추가한 구성원의 고유번호
     * @return 성공 실패여부(실패 이유설명)
     */
    @PostMapping("/invite-member")
    public String scheduleGroupMemberAdd(@Valid @RequestBody List<ScheduleGroupMemberAddVDTO> scheduleList) {
        return scheduleService.scheduleGroupMemberAdd(scheduleList);
    }

    /**
     * 조회 하고 싶은 일정의 일시, 그룹의 정보를 받아 정보 조회
     * @param schedule 필수 값:
     *                 - Integer schedSuleGroupId : 조회 할 그룹의 고유번호
     *                 - Integer empId : 조회하는 사원의 고유번호
     *                 - Timestamp scheduleDate : 조회하고 싶은 날짜/시간
     * @return 성공 실패여부(실패 이유설명)
     */
    @PostMapping("")
    public String scheduleSearch(@Valid @RequestBody ScheduleSearchVDTO schedule) throws JsonProcessingException {
        return scheduleService.selectSchedule(schedule);
    }

    /**
     * 일정 삭제 기능
     * @param schedule 필수 값:
     *                 - Integer scheduleNo : 삭제할 일정의 고유 번호
     *                 - empId : 삭제할 그룹의 그룹장 사원의 고유 번호
     * @return 성공 실패여부(실패 이유설명)
     */
    @DeleteMapping("/delete")
    public String scheduleDelete(@Valid @RequestBody ScheduleDeleteVDTO schedule) {
        return scheduleService.deleteSchedule(schedule);
    }
}
