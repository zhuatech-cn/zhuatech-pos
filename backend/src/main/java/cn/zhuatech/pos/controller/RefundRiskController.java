/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.pos.controller;

import cn.zhuatech.pos.common.ApiResponse;
import cn.zhuatech.pos.service.RefundRiskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pos/insights")
public class RefundRiskController {
    private final RefundRiskService service;
    public RefundRiskController(RefundRiskService service) { this.service = service; }

    @PostMapping("/refund-risk")
    public ApiResponse<RefundRiskService.Result> assess(@Valid @RequestBody RefundRiskService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
