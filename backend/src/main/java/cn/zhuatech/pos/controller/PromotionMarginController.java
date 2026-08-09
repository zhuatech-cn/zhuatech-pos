/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.pos.controller;

import cn.zhuatech.pos.common.ApiResponse;
import cn.zhuatech.pos.service.PromotionMarginGuardService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pos")
public class PromotionMarginController {
    private final PromotionMarginGuardService service;

    public PromotionMarginController(PromotionMarginGuardService service) {
        this.service = service;
    }

    @PostMapping("/promotion-margin-guard")
    public ApiResponse<PromotionMarginGuardService.Result> evaluate(
        @Valid @RequestBody PromotionMarginGuardService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
