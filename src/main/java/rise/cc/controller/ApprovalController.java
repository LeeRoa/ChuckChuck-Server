package rise.cc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rise.cc.dto.Approval;
import rise.cc.service.ApprovalService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/approval")
public class ApprovalController {

    private final ApprovalService approvalService;

    @PostMapping("")
    public String createApproval(@RequestBody Approval approval) {
        return approvalService.createApproval(approval);
    }

    @PatchMapping("")
    public String updateApproval(@RequestBody Approval approval) {
        return approvalService.updateApproval(approval);
    }

    @PatchMapping("/delete")
    public String deleteApproval(@RequestBody Approval approval) {
        return approvalService.deleteApproval(approval);
    }

    @GetMapping("")
    public String findApproval(Approval approval) {
        return approvalService.getApproval(approval);
    }
}
