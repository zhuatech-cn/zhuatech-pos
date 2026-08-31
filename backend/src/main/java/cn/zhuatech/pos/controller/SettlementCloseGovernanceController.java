/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.controller;

import cn.zhuatech.pos.common.ApiResponse;
import cn.zhuatech.pos.service.SettlementCloseGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/pos")
public class SettlementCloseGovernanceController {
    private final SettlementCloseGovernanceService service;
    public SettlementCloseGovernanceController(SettlementCloseGovernanceService service) { this.service = service; }

    @PostMapping("/settlement-close")
    public ApiResponse<SettlementCloseGovernanceService.Assessment> assess(
            @Valid @RequestBody SettlementCloseGovernanceService.Request request) {
        return ApiResponse.ok("POS 结算关班评估完成", service.assess(request));
    }
}
