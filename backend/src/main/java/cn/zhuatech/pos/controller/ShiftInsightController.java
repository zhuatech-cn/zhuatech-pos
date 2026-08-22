/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.controller;

import cn.zhuatech.pos.common.ApiResponse;
import cn.zhuatech.pos.service.ShiftReconciliationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pos")
public class ShiftInsightController {
    private final ShiftReconciliationService service;
    public ShiftInsightController(ShiftReconciliationService service) { this.service = service; }

    @PostMapping("/shift-reconciliation")
    public ApiResponse<ShiftReconciliationService.Result> reconcile(@Valid @RequestBody ShiftReconciliationService.Request request) {
        return ApiResponse.ok(service.reconcile(request));
    }
}
